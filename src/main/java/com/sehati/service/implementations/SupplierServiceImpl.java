package com.sehati.service.implementations;

import com.sehati.exception.EntityExistException;
import com.sehati.exception.NotFoundException;
import com.sehati.model.Supplier;
import com.sehati.repository.ISupplierRepository;
import com.sehati.service.interfaces.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements ISupplierService {
    private ISupplierRepository supplierRepository;

    public SupplierServiceImpl(@Autowired ISupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }
    @Override
    public List<Supplier> list() throws Exception {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier getById(Integer id) throws Exception {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if(supplierOptional.isEmpty()){
            throw new NotFoundException("Supplier not found");
        }
        return supplierOptional.get();
    }

    @Override
    public Supplier create(Supplier supplier) throws Exception {
        try {
            Supplier newSupplier = supplierRepository.save(supplier);
            return newSupplier;
        }catch (Exception e){
            throw new EntityExistException();
        }
    }

    @Override
    public Supplier update(Supplier supplier, Integer id) throws Exception {
        Optional<Supplier> vendorProductPrice = supplierRepository.findById(id);
        if(vendorProductPrice.isEmpty()){
            throw new EntityExistException("Supplier doesnt exist!");
        }
        try {
            Supplier existingSupplier = getById(id);
            existingSupplier.setSupplierName(supplier.getSupplierName());

            return supplierRepository.save(existingSupplier);
        }catch (NotFoundException e){
            throw new NotFoundException("Update Failed because ID is not found");
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try {
            Supplier existingSupplier = getById(id);
            supplierRepository.delete(existingSupplier);
        } catch (NotFoundException e) {
            throw new NotFoundException("Delete failed because ID is not found");
        }
    }
}
