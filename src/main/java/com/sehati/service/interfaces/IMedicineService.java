package com.sehati.service.interfaces;

import com.sehati.model.Medicine;
import com.sehati.model.MedicineSupplier;
import com.sehati.model.request.MedicinePriceRequest;
import com.sehati.model.request.MedicineSupplierRequest;

import java.util.List;

public interface IMedicineService {
    List<MedicineSupplier> list() throws Exception;

    MedicineSupplier getById(Integer id) throws Exception;

    MedicineSupplier create(MedicineSupplierRequest medicine) throws Exception;

    MedicineSupplier update(MedicinePriceRequest medicinePriceRequest, Integer id) throws Exception;
}
