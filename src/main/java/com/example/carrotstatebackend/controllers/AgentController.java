package com.example.carrotstatebackend.controllers;

import com.example.carrotstatebackend.controllers.dtos.request.persons.BaseAgentRequest;
import com.example.carrotstatebackend.controllers.dtos.request.persons.UpdateCredentialsRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.services.interfaces.persons.IAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("agent")
public class AgentController{

    @Autowired
    private IAgentService service;

    @GetMapping("/real_state/{realStateId}")
    public ResponseEntity<BaseResponse> getByRealState(@PathVariable Long realStateId){
        BaseResponse response = service.listByRealState(realStateId);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable Long id){
        BaseResponse response = service.get(id);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid BaseAgentRequest request){
        BaseResponse response = service.create(request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PutMapping("/credentials/{idAgent}")
    public ResponseEntity<BaseResponse> update(@RequestBody @Valid UpdateCredentialsRequest request,
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
