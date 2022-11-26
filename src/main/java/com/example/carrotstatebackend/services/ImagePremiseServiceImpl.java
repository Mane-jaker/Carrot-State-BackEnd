package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.CreateImageRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetImageResponse;
import com.example.carrotstatebackend.entities.pivots.ImagePremise;
import com.example.carrotstatebackend.repositories.pivots.IImagePremiseRepository;
import com.example.carrotstatebackend.services.interfaces.pivtos.IBaseImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("imgPremise")
public class ImagePremiseServiceImpl implements IBaseImageService {

    @Autowired
    private IImagePremiseRepository repository;

    @Override
    public List<GetImageResponse> list() {
        return repository.findAll().stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public void saveImage(CreateImageRequest request) {
        ImagePremise imagePremise = new ImagePremise();
        imagePremise.setPremise(request.getPremise());
        imagePremise.setUrl(request.getUrl());
        repository.save(imagePremise);
    }

    private GetImageResponse from(ImagePremise imagePremise){
        return GetImageResponse.builder().url(imagePremise.getUrl()).build();
    }
}
