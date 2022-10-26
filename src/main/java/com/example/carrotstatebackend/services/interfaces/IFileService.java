package com.example.carrotstatebackend.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileService {

    String uploadManagerProfilePicture(MultipartFile multipartFile, Long id);

    String uploadAgentProfilePicture(MultipartFile multipartFile, Long id);

    String uploadHousePicture(MultipartFile multipartFile, Long idHouse, Long idAgent);

    String uploadPlotPicture(MultipartFile multipartFile, Long id);

    String uploadPremisePicture(MultipartFile multipartFile, Long id);

}

 
