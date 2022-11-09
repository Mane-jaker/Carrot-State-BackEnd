package com.example.carrotstatebackend.controllers;

import com.example.carrotstatebackend.controllers.dtos.request.CreatePlotRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdatePlotRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.services.interfaces.IPlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("plot")
public class PlotController {

    @Autowired
    private IPlotService service;

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable Long id ){
        BaseResponse response = service.get(id);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/list/agent/{idAgent}")
    public ResponseEntity<BaseResponse> list(@PathVariable Long idAgent){
        BaseResponse response = service.listByAgent(idAgent);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/agent/{idAgent}")
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid CreatePlotRequest request,
                                               @PathVariable Long idAgent){
        BaseResponse response = service.create(request, idAgent);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PutMapping("{idPlot}")
    public ResponseEntity<BaseResponse> update(@RequestBody @Valid UpdatePlotRequest request,
                                               @PathVariable Long idPlot){
        BaseResponse response = service.update(idPlot, request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
