package com.example.carrotstatebackend.services.interfaces.properties;

import com.example.carrotstatebackend.controllers.dtos.request.properties.BasePropertyRequest;
import com.example.carrotstatebackend.controllers.dtos.request.RequestFilters;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.entities.Client;

public interface IBasePropertyService <E>{
    BaseResponse list();
    BaseResponse listByAgent(Long idAgent);
    BaseResponse get(Long id);
    BaseResponse search(String keyWord, RequestFilters filters);
    BaseResponse create(BasePropertyRequest request, Long idAgent);
    BaseResponse update(Long idProperty, BasePropertyRequest request);
    BaseResponse delete(Long id);
    BaseResponse updateToSoldOut(Long id, Client owner);
    E getPropertyE(Long id);
}
