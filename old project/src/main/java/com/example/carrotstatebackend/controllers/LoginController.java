package com.example.carrotstatebackend.controllers;

import com.example.carrotstatebackend.controllers.dtos.request.LoginRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.services.interfaces.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("loggin")
@CrossOrigin("http://127.0.0.1:5173")
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @GetMapping("/manager")
    public ResponseEntity<BaseResponse> managerLogin(@RequestBody @Valid LoginRequest request){
        BaseResponse response = loginService.managerLogin(request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/aggent")
    public ResponseEntity<BaseResponse> agentLogin(@RequestBody @Valid LoginRequest request){
        BaseResponse response = loginService.agentLogin(request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
