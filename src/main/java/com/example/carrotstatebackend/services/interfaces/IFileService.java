package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    BaseResponse list();

    BaseResponse uploadRealStateProfilePicture(MultipartFile multipartFile, Long id);

    BaseResponse uploadAgentProfilePicture(MultipartFile multipartFile, Long id);

    BaseResponse uploadHousePicture(MultipartFile multipartFile, Long idHouse);

    BaseResponse uploadPlotPicture(MultipartFile multipartFile, Long idPlot);

    BaseResponse uploadPremisePicture(MultipartFile multipartFile, Long idPremise);

}

 
