package com.example.carrotstatebackend.controllers;

import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.services.interfaces.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("image")
public class FileController {

    @Autowired
    IFileService service;

    @GetMapping
    public ResponseEntity<BaseResponse> list(){
        BaseResponse response = service.list();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("real_state/{id}/profile")
    public ResponseEntity<BaseResponse> uploadRealStateProfilePicture(MultipartFile file, @PathVariable Long id){
        BaseResponse response = service.uploadRealStateProfilePicture(file, id);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("agent/{id}/profile")
    public ResponseEntity<BaseResponse> uploadAgentProfilePicture(MultipartFile file, @PathVariable Long id){
        BaseResponse response = service.uploadAgentProfilePicture(file, id);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("client/{id}/profile")
    public ResponseEntity<BaseResponse> uploadClientProfilePicture(MultipartFile file,
                                                                   @PathVariable Long id){
        return null;
    }

    @PostMapping("/house/{idHouse}")
    public ResponseEntity<BaseResponse> uploadHousePicture(MultipartFile file,
                                                         @PathVariable Long idHouse) {
        BaseResponse response = service.uploadHousePicture(file, idHouse);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/plot/{idPlot}")
    public ResponseEntity<BaseResponse> uploadPlotPicture(MultipartFile file,
                                                          @PathVariable Long idPlot){
        BaseResponse response = service.uploadPlotPicture(file, idPlot);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/premise/{idPremise}")
    public ResponseEntity<BaseResponse> uploadPremisePicture(MultipartFile file,
                                                             @PathVariable Long idPremise){
        BaseResponse response = service.uploadPremisePicture(file, idPremise);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

}
