package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreateImageRequest;
import com.example.carrotstatebackend.entities.Agent;
import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.Plot;
import com.example.carrotstatebackend.entities.Premise;

public interface IImageService{

    void saveHouseImage(CreateImageRequest request);
    void savePremiseImage(CreateImageRequest request);
    void savePlotImage(CreateImageRequest request);

} 
 // si xddd es para las relaciones lo vimos hoy con christian ps
// Interface Image Service xD
//iimageservice???
