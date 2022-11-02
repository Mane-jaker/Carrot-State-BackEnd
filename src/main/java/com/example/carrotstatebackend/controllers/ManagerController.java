package com.example.carrotstatebackend.controllers;

import com.example.carrotstatebackend.controllers.dtos.request.CreateManagerRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateManagerRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetManagerResponse;
import com.example.carrotstatebackend.services.interfaces.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("manager")
public class ManagerController {

    @Autowired
    private IManagerService service;

    @GetMapping
    public List<GetManagerResponse> list(){
        return service.list();
    }

    @GetMapping("{id}")
    public GetManagerResponse get(@PathVariable Long id ){
        return service.get(id);
    }

    @PostMapping
    public GetManagerResponse create(@RequestBody CreateManagerRequest request){
        return service.create(request);
    }

    @PutMapping("{id}")
    public GetManagerResponse update(@PathVariable Long id, @RequestBody UpdateManagerRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long id){
        service.delete(id);
    }
}
