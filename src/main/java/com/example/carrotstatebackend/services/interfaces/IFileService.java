package com.example.carrotstatebackend.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileService {

    String uploadManagerProfilePicture(MultipartFile multipartFile, Long id);

    String uploadAgentProfilePicture(MultipartFile multipartFile, Long id);

    String uploadHousePicture(List<MultipartFile> multipartFile, Long id);

    String uploadPlotPicture(List<MultipartFile> multipartFile, Long id);

    String uploadPremisePicture(List<MultipartFile> multipartFile, Long id);

}

 
