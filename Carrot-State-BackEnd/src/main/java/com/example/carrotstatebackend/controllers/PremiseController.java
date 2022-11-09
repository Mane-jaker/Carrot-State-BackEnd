package com.example.carrotstatebackend.controllers;
import com.example.carrotstatebackend.controllers.dtos.request.CreatePremiseRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdatePremiseRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.services.interfaces.IPremiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("premise")
public class PremiseController {

    @Autowired
    private IPremiseService service;

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable Long id){
        BaseResponse response = service.get(id);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("agent/{idAgent}")
    public ResponseEntity<BaseResponse> list(@PathVariable Long idAgent) {
        BaseResponse response = service.listByAgent(idAgent);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/agent/{idAgent}")
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid CreatePremiseRequest request,
                                               @PathVariable Long idAgent){
        BaseResponse response = service.create(request, idAgent);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PutMapping("idPremise")
    public ResponseEntity<BaseResponse> update(@RequestBody @Valid UpdatePremiseRequest request,
                                               @PathVariable Long idPremise){
        BaseResponse response = service.update(idPremise, request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long id){
        service.delete(id);
    }
}
