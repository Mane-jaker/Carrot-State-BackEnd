package com.example.carrotstatebackend.services; 
import com.example.carrotstatebackend.controllers.dtos.request.CreateImageRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetImageResponse;
import com.example.carrotstatebackend.entities.pivots.ImageHouse;
import com.example.carrotstatebackend.repositories.IImageHouseRepository;
import com.example.carrotstatebackend.services.interfaces.IImageHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageHouseServiceImpl implements IImageHouseService {

    @Autowired
    IImageHouseRepository repository;


    @Override
    public List<GetImageResponse> list() {
        return repository.findAll().stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public void saveImage(CreateImageRequest request) {
        ImageHouse imageHouse = new ImageHouse();
        imageHouse.setHouse(request.getHouse());
        imageHouse.setUrl(request.getUrl());
        repository.save(imageHouse);
    }

    private GetImageResponse from(ImageHouse imageHouse){
        return GetImageResponse.builder().url(imageHouse.getUrl()).build();
    }
}
 
