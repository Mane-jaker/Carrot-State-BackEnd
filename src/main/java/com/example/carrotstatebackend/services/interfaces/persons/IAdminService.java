package com.example.carrotstatebackend.services.interfaces.persons;

import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.entities.Admin;
import com.example.carrotstatebackend.entities.RealState;

import java.util.Optional;

public interface IAdminService {
    BaseResponse getById(Integer id);
    Admin getAdmin(Integer id);
    Admin getAdmin(String email);
}
