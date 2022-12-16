package com.sehati.service.interfaces;

import com.sehati.model.Supplier;

import java.util.List;

public interface ISupplierService {
    List<Supplier> list() throws Exception;

    Supplier getById(Integer id) throws Exception;

    Supplier create(Supplier supplier) throws Exception;

    Supplier update(Supplier supplier, Integer id) throws Exception;

    void delete(Integer id) throws Exception;
}
