package com.example.carrotstatebackend.controllers;

import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.services.interfaces.ISoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sold")
public class SoldController {

    @Autowired
    private ISoldService service;

    @GetMapping
    public ResponseEntity<BaseResponse> list(){
        BaseResponse response = service.list();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
