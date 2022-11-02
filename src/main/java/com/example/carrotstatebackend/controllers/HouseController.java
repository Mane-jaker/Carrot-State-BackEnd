package com.example.carrotstatebackend.controllers;


import com.example.carrotstatebackend.controllers.dtos.request.CreateHouseRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateHouseRequest;
import com.example.carrotstatebackend.services.interfaces.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.carrotstatebackend.controllers.dtos.response.GetHouseResponse;

import java.util.List;

@RestController
@RequestMapping("House")
public class HouseController {

    @Autowired
    private IHouseService service;

    @GetMapping
    public List<GetHouseResponse> list(){
        return service.list();
    }

    @GetMapping("{id}")
    public GetHouseResponse get(@PathVariable Long id ){
        return service.get(id);
    }

    @PostMapping
    public GetHouseResponse create(@RequestBody CreateHouseRequest request){
        return service.create(request);
    }

    @PutMapping("{id}")
    public GetHouseResponse update(@PathVariable Long id, @RequestBody UpdateHouseRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long id){
        service.delete(id);
    }
}
