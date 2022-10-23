package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.response.GetManagerResponse;

import java.util.List;

public interface IManagerService {

    List<GetManagerResponse> list();

}
