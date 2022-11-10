package com.example.carrotstatebackend.controllers;


import com.example.carrotstatebackend.controllers.dtos.request.CreateHouseRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateHouseRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.services.interfaces.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:5173/")
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

    @PostMapping("/agent/{idAgent}")
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid CreateHouseRequest request,
                                               @PathVariable Long idAgent){
        BaseResponse response = service.create(request, idAgent);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PutMapping("{idHouse}")
    public ResponseEntity<BaseResponse> update(@RequestBody @Valid UpdateHouseRequest request,
                                                @PathVariable Long idHouse){
        BaseResponse response = service.update(idHouse, request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    //royer xd encargate del put de plot, mientras hago el de house puedes guiarte de este si quieres

}
