package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.CreateImageRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetImageResponse;
import com.example.carrotstatebackend.entities.pivots.ImagePlot;
import com.example.carrotstatebackend.repositories.pivots.IImagePlotRepository;
import com.example.carrotstatebackend.services.interfaces.pivtos.IBaseImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("imgPlot")
public class ImagePlotServiceImpl implements IBaseImageService {

    @Autowired
    private IImagePlotRepository repository;


    @Override
    public List<GetImageResponse> list() {
        return repository.findAll().stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public void saveImage(CreateImageRequest request) {
        ImagePlot imagePlot = new ImagePlot();
        imagePlot.setPlot(request.getPlot());
        imagePlot.setUrl(request.getUrl());
        repository.save(imagePlot);
    }

    private GetImageResponse from(ImagePlot imagePlot){
        return GetImageResponse.builder().url(imagePlot.getUrl()).build();
    }
}
