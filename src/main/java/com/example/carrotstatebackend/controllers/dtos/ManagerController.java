package com.example.carrotstatebackend.controllers.dtos;

import com.example.carrotstatebackend.controllers.dtos.response.CreateManagerResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("manager")
public class ManagerController {

    @GetMapping
    public List<CreateManagerResponse> list(){
        return null;
    }


}
