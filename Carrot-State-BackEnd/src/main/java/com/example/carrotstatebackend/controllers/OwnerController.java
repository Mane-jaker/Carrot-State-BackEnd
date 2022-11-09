package com.example.carrotstatebackend.controllers;

import com.example.carrotstatebackend.controllers.dtos.request.CreateOwnerRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.services.interfaces.IOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("owner")
public class OwnerController {

    @Autowired
    private IOwnerService service;

    @GetMapping
    public ResponseEntity<BaseResponse> list() {
        BaseResponse response = service.list();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable Long id) {
        BaseResponse response = service.get(id);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/house/agent/{idAgent}")
    public ResponseEntity<BaseResponse> createHouseOwner(@RequestBody @Valid CreateOwnerRequest request,
                                                         @PathVariable Long idAgent) {
       BaseResponse response = service.createHouseOwner(request, idAgent);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/plot/agent/{idAgent}")
    public ResponseEntity<BaseResponse> createPlotOwner(@RequestBody @Valid CreateOwnerRequest request,
                                                        @PathVariable Long idAgent){
        BaseResponse response = service.createPlotOwner(request, idAgent);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("premise/agent/{idAgent}")
    public ResponseEntity<BaseResponse> createPremiseOwner(@RequestBody @Valid CreateOwnerRequest request,
                                                           @PathVariable Long idAgent){
        BaseResponse response = service.createPremiseOwner(request, idAgent);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long id) { service.delete(id);}
}
