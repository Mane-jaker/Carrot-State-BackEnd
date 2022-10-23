package com.example.carrotstatebackend.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileService {

    String uploadManagerProfilePicture(MultipartFile multipartFile);

    String uploadAgentProfilePicture(MultipartFile multipartFile);

    String uploadHousePicture(List<MultipartFile> multipartFile);

    String uploadPlotPicture(List<MultipartFile> multipartFile);

    String uploadPremisePicture(List<MultipartFile> multipartFile);

}
