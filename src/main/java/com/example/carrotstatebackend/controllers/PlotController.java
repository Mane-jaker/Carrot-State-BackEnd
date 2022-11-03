package com.example.carrotstatebackend.controllers;

import com.example.carrotstatebackend.controllers.dtos.request.CreatePlotRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdatePlotRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetPlotResponse;
import com.example.carrotstatebackend.services.interfaces.IPlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("plot")
public class PlotController {

    @Autowired
    private IPlotService service;

    @GetMapping
    public List<GetPlotResponse> list(){
        return service.list();
    }

    @GetMapping("{id}")
    public GetPlotResponse get(@PathVariable Long id ){
        return service.get(id);
    }

    @PostMapping
    public GetPlotResponse create(@RequestBody CreatePlotRequest request){
        return service.create(request);
    }

    @PutMapping("{id}")
    public GetPlotResponse update(@PathVariable Long id, @RequestBody UpdatePlotRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long id){
        service.delete(id);
    }

}
