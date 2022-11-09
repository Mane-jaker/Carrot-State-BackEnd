package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    BaseResponse uploadManagerProfilePicture(MultipartFile multipartFile, Long id);

    BaseResponse uploadAgentProfilePicture(MultipartFile multipartFile, Long id);

    BaseResponse uploadHousePicture(MultipartFile multipartFile, Long idHouse, Long idAgent);

    BaseResponse uploadPlotPicture(MultipartFile multipartFile, Long idPlot, Long idAgent);

    BaseResponse uploadPremisePicture(MultipartFile multipartFile, Long idPremise, Long idAgent);

}

 
