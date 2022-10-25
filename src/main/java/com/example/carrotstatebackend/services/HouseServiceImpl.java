package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.CreateHouseRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateHouseRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetHouseResponse;
import com.example.carrotstatebackend.repositories.IHouseRepository;
import com.example.carrotstatebackend.services.interfaces.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements IHouseService{

    @Autowired
    IHouseRepository repository;

    @Override
    public List<GetHouseResponse> list() {
        return null;
    }

    @Override
    public GetHouseResponse get(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public GetHouseResponse create(CreateHouseRequest request) {
        return null;
    }

    @Override
    public GetHouseResponse update(Long id, UpdateHouseRequest request) {
        return null;
    }

    @Override
    public void updateHouseProfile(String fileUrl, Long idHouse) {

    }
}
