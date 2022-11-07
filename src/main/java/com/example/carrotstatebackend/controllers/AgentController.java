package com.example.carrotstatebackend.controllers;

import com.example.carrotstatebackend.controllers.dtos.request.CreateAgentRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.services.interfaces.IAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequestMapping("agent")
public class AgentController{

    @Autowired
    private IAgentService service;

    @GetMapping("/manager/{managerId}")
    public ResponseEntity<BaseResponse> getByManager(@PathVariable Long managerId){
        BaseResponse response = service.listByManager(managerId);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable Long id){
        BaseResponse response = service.get(id);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid CreateAgentRequest request){
        BaseResponse response = service.create(request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long id){
        service.delete(id);
    }
}
