package com.example.carrotstatebackend.controllers;


import com.example.carrotstatebackend.controllers.dtos.request.CreateHouseRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateHouseRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.services.interfaces.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.carrotstatebackend.controllers.dtos.response.GetHouseResponse;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("house")
public class HouseController {

    @Autowired
    private IHouseService service;

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable Long id ){
        BaseResponse response = service.get(id);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/agent/{idAgent}")
    public ResponseEntity<BaseResponse> listByAgent(@PathVariable Long idAgent){
        BaseResponse response = service.listByAgent(idAgent);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/owner/{idOwner}")
    public ResponseEntity<BaseResponse> listByOwner(@PathVariable Long idOwner){
        return null;
    }

    @PostMapping("/agent/{idAgent}")
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid CreateHouseRequest request,
                                               @PathVariable Long idAgent){
        BaseResponse response = service.create(request, idAgent);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

}
