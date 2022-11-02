package com.example.carrotstatebackend.controllers;

import com.example.carrotstatebackend.controllers.dtos.request.CreateAgentRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateAgentRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetAgentResponse;
import com.example.carrotstatebackend.services.interfaces.IAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("Agent")
public class AgentController{

    @Autowired
    private IAgentService service;

    @GetMapping
    public List<GetAgentResponse> list(){
        return service.list();
    }

    @GetMapping("{id}")
    public GetAgentResponse get(@PathVariable Long id){return service.get(id);}

    @PostMapping
    public GetAgentResponse create(@RequestBody CreateAgentRequest request){
        return service.create(request);
    }

    @PutMapping("{id}")
    public GetAgentResponse update(@PathVariable Long id, @RequestBody UpdateAgentRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long id){
        service.delete(id);
    }
}
