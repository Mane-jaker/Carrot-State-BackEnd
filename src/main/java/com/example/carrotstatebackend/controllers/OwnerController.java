package com.example.carrotstatebackend.controllers;

import com.example.carrotstatebackend.controllers.dtos.request.CreateOwnerRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateOwnerRequest;
import com.example.carrotstatebackend.controllers.dtos.response.CreateOwnerResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetOwnerResponse;
import com.example.carrotstatebackend.services.interfaces.IOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Owner")
public class OwnerController {

    @Autowired
    private IOwnerService service;

    @GetMapping
    public List<GetOwnerResponse> list() { return service.list(); }

    @GetMapping("{id}")
    public GetOwnerResponse get(@PathVariable Long id) { return service.get(id);}

    @PostMapping
    public GetOwnerResponse create(@RequestBody CreateOwnerRequest request) { return service.create(request); }

    @PutMapping("{id}")
    public GetOwnerResponse update(@PathVariable Long id, @RequestBody UpdateOwnerRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long id) { service.delete(id);}
}
