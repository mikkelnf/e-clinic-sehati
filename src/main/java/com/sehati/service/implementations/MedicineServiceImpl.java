package com.sehati.service.implementations;

import com.sehati.exception.EntityExistException;
import com.sehati.exception.NotFoundException;
import com.sehati.model.Medicine;
import com.sehati.model.MedicineSupplier;
import com.sehati.model.Supplier;
import com.sehati.model.request.MedicinePriceRequest;
import com.sehati.model.request.MedicineSupplierRequest;
import com.sehati.repository.IMedicineRepository;
import com.sehati.repository.IMedicineSupplierRepository;
import com.sehati.repository.ISupplierRepository;
import com.sehati.service.interfaces.IMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineServiceImpl implements IMedicineService {
    private IMedicineRepository medicineRepository;

    private ISupplierRepository supplierRepository;

    private IMedicineSupplierRepository medicineSupplierRepository;

    public MedicineServiceImpl(@Autowired IMedicineRepository medicineRepository,
                               @Autowired ISupplierRepository supplierRepository,
                               @Autowired IMedicineSupplierRepository medicineSupplierRepository) {
        this.medicineRepository = medicineRepository;
        this.supplierRepository = supplierRepository;
        this.medicineSupplierRepository = medicineSupplierRepository;
    }

    @Override
    public List<MedicineSupplier> list() throws Exception {
        return medicineSupplierRepository.findAll();
    }

    @Override
    public MedicineSupplier getById(Integer id) throws Exception {
        Optional<MedicineSupplier> medicineSupplierOptional = medicineSupplierRepository.findById(id);
        if(medicineSupplierOptional.isEmpty()){
            throw new NotFoundException("Medicine not found");
        }
        return medicineSupplierOptional.get();
    }

    @Override
    public MedicineSupplier create(MedicineSupplierRequest medicineSupplierRequest) throws Exception {
        Integer supplierId = medicineSupplierRequest.getSupplierId();
        Optional<Supplier> existingSupplier = supplierRepository.findById(supplierId);
        if(existingSupplier.isEmpty()){
            throw new EntityExistException("Supplier doesnt exist!");
        }
        try {
            Medicine medicineDto = new Medicine();
            medicineDto.setMedicineName(medicineSupplierRequest.getMedicineName());

            Supplier supplierDto = existingSupplier.get();

            MedicineSupplier newMedicineSupplier = new MedicineSupplier();
            newMedicineSupplier.setMedicine(medicineDto);
            newMedicineSupplier.setSupplier(supplierDto);
            newMedicineSupplier.setMedicinePrice(medicineSupplierRequest.getMedicinePrice());

            try {
                medicineRepository.save(medicineDto);
            }catch (Exception e){
                Medicine existingMedicine = medicineRepository.getByName(medicineSupplierRequest.getMedicineName());
                medicineDto.setMedicineId(existingMedicine.getMedicineId());
                newMedicineSupplier.setMedicine(medicineDto);

                medicineSupplierRepository.save(newMedicineSupplier);
                return newMedicineSupplier;
            }
            medicineSupplierRepository.save(newMedicineSupplier);

            return newMedicineSupplier;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public MedicineSupplier update(MedicinePriceRequest medicinePriceRequest, Integer id) throws Exception {
        Optional<MedicineSupplier> medicineSupplier = medicineSupplierRepository.findById(id);
        if(medicineSupplier.isEmpty()){
            throw new EntityExistException("Medicine Supplier doesnt exist!");
        }
        try {
            MedicineSupplier existingMedicineSupplier = getById(id);
            existingMedicineSupplier.setActive(false);

            MedicineSupplier newMedicineSupplier = new MedicineSupplier();
            newMedicineSupplier.setMedicine(existingMedicineSupplier.getMedicine());
            newMedicineSupplier.setSupplier(existingMedicineSupplier.getSupplier());
            newMedicineSupplier.setMedicinePrice(medicinePriceRequest.getMedicinePrice());

            medicineSupplierRepository.save(existingMedicineSupplier);
            return medicineSupplierRepository.save(newMedicineSupplier);
        }catch (NotFoundException e){
            throw new NotFoundException("Update Failed because ID is not found");
        }
    }
}
