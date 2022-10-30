package com.example.carrotstatebackend.controllers;

import com.example.carrotstatebackend.controllers.dtos.response.GetOwnerResponse;
import com.example.carrotstatebackend.services.interfaces.IOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("Owner")
public class OwnerController {

    @Autowired
    private IOwnerService service;

    @GetMapping
    public List<GetOwnerResponse> list() { return service.list(); }

    @GetMapping("{id}")
    public GetOwnerResponse get(@PathVariable Long id) { return null;}
}
