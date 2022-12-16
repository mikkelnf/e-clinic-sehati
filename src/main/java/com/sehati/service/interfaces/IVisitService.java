package com.sehati.service.interfaces;

import com.sehati.model.Visit;
import com.sehati.model.Visit;
import com.sehati.model.request.VisitRequest;

import java.util.List;

public interface IVisitService {
    List<Visit> list() throws Exception;

    List<Visit> getByPatientId(Integer id) throws Exception;

    Visit create(VisitRequest visitRequest) throws Exception;

    Visit update(VisitRequest visitRequest, Integer id) throws Exception;
}
