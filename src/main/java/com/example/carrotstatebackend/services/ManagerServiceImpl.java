package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.response.GetManagerResponse;
import com.example.carrotstatebackend.repositories.IManagerRepository;
import com.example.carrotstatebackend.services.interfaces.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class ManagerServiceImpl implements IManagerService {
    @Autowired
    private IManagerRepository repository;

    @Override
    public List<GetManagerResponse> list() {
        //return repository.findAll().stream().map().collect(Collectors.toList());
        return null;
    }
}
