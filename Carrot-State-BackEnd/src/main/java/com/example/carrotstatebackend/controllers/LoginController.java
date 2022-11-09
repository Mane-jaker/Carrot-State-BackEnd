package com.example.carrotstatebackend.controllers;

import com.example.carrotstatebackend.controllers.dtos.request.LoginRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.services.interfaces.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("login")
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @GetMapping("/manager")
    public ResponseEntity<BaseResponse> managerLogin(@RequestBody @Valid LoginRequest request){
        BaseResponse response = loginService.managerLogin(request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
    
    @PostMapping("/agent")
    public ResponseEntity<BaseResponse> agentLog(@RequestBody @Valid LoginRequest request){
        BaseResponse response = loginService.agentLogin(request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
