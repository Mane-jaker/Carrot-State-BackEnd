package com.example.carrotstatebackend.controllers;

import com.example.carrotstatebackend.controllers.dtos.request.CreateManagerRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateCommissionRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateManagerCredentialsRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.services.interfaces.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("manager")
public class ManagerController {

    @Autowired
    private IManagerService service;

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable Long id){
        BaseResponse response = service.get(id);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping
    public ResponseEntity<BaseResponse> list(){
        BaseResponse response = service.list();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@Valid @RequestBody CreateManagerRequest request){
        BaseResponse response = service.create(request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PutMapping("/credentials/{idManager}")
    public ResponseEntity<BaseResponse> updateCrendentials(@RequestBody @Valid UpdateManagerCredentialsRequest request,
                                               @PathVariable Long idManager){
        BaseResponse response = service.updateCredentials(request, idManager);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PutMapping("/commission/{idManager}")
    public ResponseEntity<BaseResponse> updateCommision(@RequestBody @Valid UpdateCommissionRequest commission,
                                               @PathVariable Long idManager){
        BaseResponse response = service.updateCommision(commission.getCommission(), idManager);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
