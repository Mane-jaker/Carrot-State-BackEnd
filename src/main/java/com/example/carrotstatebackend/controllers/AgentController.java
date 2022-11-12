package com.example.carrotstatebackend.controllers;

import com.example.carrotstatebackend.controllers.dtos.request.CreateAgentRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateAgentCredentialsRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateAgentRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.services.interfaces.IAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:5173/")
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

    @PutMapping("/credentials/{idAgent}")
    public ResponseEntity<BaseResponse> update(@RequestBody @Valid UpdateAgentCredentialsRequest request,
                                               @PathVariable Long idAgent){
        BaseResponse response = service.updateCredentials(request, idAgent);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PutMapping("/{idAgent}/status/{status}")
    public ResponseEntity<BaseResponse> updateStatus(@PathVariable Boolean status,
                                               @PathVariable Long idAgent){
        BaseResponse response = service.changeStatus(status, idAgent);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
