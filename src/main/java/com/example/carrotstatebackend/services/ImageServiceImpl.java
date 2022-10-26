package com.example.carrotstatebackend.services; 
import com.example.carrotstatebackend.controllers.dtos.request.CreateImageRequest;
import com.example.carrotstatebackend.entities.Image;
import com.example.carrotstatebackend.repositories.IImageRepository;
import com.example.carrotstatebackend.services.interfaces.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements IImageService{

    @Autowired
    IImageRepository repository;


    @Override
    public void saveHouseImage(CreateImageRequest request) {
        Image image = new Image();
        image.setHouse(request.getHouse());
        image.setUrl(request.getUrl());
        repository.save(image);
    }

    @Override
    public void savePremiseImage(CreateImageRequest request) {
        Image image = new Image();
        image.setPremise(request.getPremise());
        image.setUrl(request.getUrl());
        repository.save(image);
    }

    @Override
    public void savePlotImage(CreateImageRequest request) {
        Image image = new Image();
        image.setPlot(request.getPlot());
        image.setUrl(request.getUrl());
        repository.save(image);
    }
}
 
