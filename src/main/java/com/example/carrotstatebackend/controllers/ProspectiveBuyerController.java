package com.example.carrotstatebackend.controllers;

import com.example.carrotstatebackend.controllers.dtos.request.CreateProspectiveBuyerRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateProspectiveBuyerRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetProspectiveBuyerResponse;
import com.example.carrotstatebackend.services.interfaces.IProspectiveBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ProspectiveBuyer")
public class ProspectiveBuyerController {

    @Autowired
    private IProspectiveBuyerService service;

    @GetMapping
    public List<GetProspectiveBuyerResponse> list() { return service.list(); }

    @GetMapping("{id}")
    public GetProspectiveBuyerResponse get(@PathVariable Long id) { return service.get(id);}

    @PostMapping
    public GetProspectiveBuyerResponse create(@RequestBody CreateProspectiveBuyerRequest request){
        return service.create(request);
    }

    @PutMapping("{id}")
    public GetProspectiveBuyerResponse update(@PathVariable Long id, @RequestBody UpdateProspectiveBuyerRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long id) { service.delete(id);}
}
