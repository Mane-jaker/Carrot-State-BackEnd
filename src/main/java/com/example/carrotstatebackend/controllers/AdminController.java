package com.example.carrotstatebackend.controllers;

import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.services.interfaces.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @GetMapping("{idAmin}")
    public ResponseEntity<BaseResponse> get(@PathVariable Integer idAmin){
        BaseResponse response = adminService.getById(idAmin);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
