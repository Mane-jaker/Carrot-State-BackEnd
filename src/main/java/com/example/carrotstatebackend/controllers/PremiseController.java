package com.example.carrotstatebackend.controllers;
import com.example.carrotstatebackend.controllers.dtos.request.CreatePremiseRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdatePremiseRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetPremiseResponse;
import com.example.carrotstatebackend.services.interfaces.IPremiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("premise")
public class PremiseController {

    @Autowired
    private IPremiseService service;

    @GetMapping
    public List<GetPremiseResponse> list(){
        return service.list();
    }

    @GetMapping("{id}")
    public GetPremiseResponse get(@PathVariable Long id ){
        return service.get(id);
    }

    @PostMapping
    public GetPremiseResponse create(@RequestBody CreatePremiseRequest request){
        return service.create(request);
    }

    @PutMapping("{id}")
    public GetPremiseResponse update(@PathVariable Long id, @RequestBody UpdatePremiseRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long id){
        service.delete(id);
    }
}
