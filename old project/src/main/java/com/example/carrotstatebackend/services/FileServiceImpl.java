package com.example.carrotstatebackend.services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.carrotstatebackend.controllers.dtos.request.CreateImageRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetAgentResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetManagerResponse;
import com.example.carrotstatebackend.controllers.dtos.response.UploadImageResponse;
import com.example.carrotstatebackend.controllers.exceptions.NotValidFormatException;
import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.Plot;
import com.example.carrotstatebackend.entities.Premise;
import com.example.carrotstatebackend.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.io.FilenameUtils;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private IManagerService managerService;

    @Autowired
    private IHouseService houseService;

    @Autowired
    private IPlotService plotService;

    @Autowired
    private IPremiseService premiseService;

    @Autowired
    private IAgentService agentService;

    @Autowired
    private IImageService imageService;

    private AmazonS3 s3client;

    private String ENDPOINT_URL = "s3.us-east-2.amazonaws.com";

    private String BUCKET_NAME = "realstatebucket01";

    private final String ACCESS_KEY = "AKIA57FUC7G7ZD63OHNA";

    private final String SECRET_KEY = "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";

    @Override
    public BaseResponse uploadManagerProfilePicture(MultipartFile multipartFile, Long id) {

        GetManagerResponse manager = managerService.getResponse(id);
        String FILE_URI = "persons/manager/" + manager.getMail() + "/profile_picture/";
        String fileUrl = "";

        try{

            File file = convertMultiPartToFile(multipartFile);
            String filePath = FILE_URI + validateFormat(generateFileName(multipartFile));

            fileUrl = createUrl(filePath);
            uploadFileToS3Bucket(filePath, file);

            managerService.updateManagerProfile(fileUrl, id);
            file.delete();

        } catch (Exception e){
            e.printStackTrace();
        }

        UploadImageResponse imageResponse = UploadImageResponse.builder().url(fileUrl).build();
        return BaseResponse.builder()
                .data(imageResponse)
                .success(true)
                .httpStatus(HttpStatus.CREATED)
                .message("The image was uploaded").build();
    }

    @Override
    public BaseResponse uploadAgentProfilePicture(MultipartFile multipartFile, Long id) {

        GetAgentResponse agent = agentService.getResponse(id);
        String FILE_URI = "persons/agent/" + agent.getEmail() + "/profile_picture/";
        String fileUrl = "";

        try{
            File file = convertMultiPartToFile(multipartFile);
            String filePath = FILE_URI + validateFormat(generateFileName(multipartFile));

            fileUrl = createUrl(filePath);
            uploadFileToS3Bucket(filePath, file);

            agentService.updateAgentProfile(fileUrl, id);
            file.delete();

        } catch (Exception e){
            e.printStackTrace();
        }

        UploadImageResponse imageResponse = UploadImageResponse.builder().url(fileUrl).build();
        return BaseResponse.builder()
                .data(imageResponse)
                .success(true)
                .httpStatus(HttpStatus.CREATED)
                .message("The image was uploaded").build();
    }

    @Override
    public BaseResponse uploadHousePicture(MultipartFile multipartFile, Long idHouse, Long idAgent) {

        GetAgentResponse agent = agentService.getResponse(idAgent);
        House house = houseService.getHouse(idHouse);

        String FILE_URI = "persons/agent/"
                + agent.getEmail() + "/properties/houses/"
                + "house_" + house.getId() + "/pictures/";
        String fileUrl = "";

        try{
            File file = convertMultiPartToFile(multipartFile);
            String filePath = FILE_URI + validateFormat(generateFileName(multipartFile));
            fileUrl = createUrl(filePath);
            uploadFileToS3Bucket(filePath, file);

            CreateImageRequest request = CreateImageRequest.builder().house(house).url(fileUrl).build();
            imageService.saveHouseImage(request);

        } catch (Exception e){
            e.printStackTrace();
        }

        UploadImageResponse imageResponse = UploadImageResponse.builder().url(fileUrl).build();
        return BaseResponse.builder()
                .data(imageResponse)
                .success(true)
                .httpStatus(HttpStatus.CREATED)
                .message("The image was uploaded").build();
    }

    @Override
    public BaseResponse uploadPlotPicture(MultipartFile multipartFile,  Long idPlot, Long idAgent) {

        GetAgentResponse agent = agentService.getResponse(idAgent);
        Plot plot = plotService.getPlot(idPlot);
        String FILE_URI = "persons/agent/"
                + agent.getEmail() + "/properties/houses/"
                + "plot_" + plot.getId() + "/pictures/";
        String fileUrl = "";

        try{
            File file = convertMultiPartToFile(multipartFile);
            String filePath = FILE_URI + validateFormat(generateFileName(multipartFile));
            fileUrl = createUrl(filePath);
            uploadFileToS3Bucket(filePath, file);

            CreateImageRequest request = CreateImageRequest.builder().plot(plot).url(fileUrl).build();
            imageService.saveHouseImage(request);

        } catch (Exception e){
            e.printStackTrace();
        }

        UploadImageResponse imageResponse = UploadImageResponse.builder().url(fileUrl).build();
        return BaseResponse.builder()
                .data(imageResponse)
                .success(true)
                .httpStatus(HttpStatus.CREATED)
                .message("The image was uploaded").build();
    }

    @Override
    public BaseResponse uploadPremisePicture(MultipartFile multipartFile,  Long idPremise, Long idAgent) {

        GetAgentResponse agent = agentService.getResponse(idAgent);
        Premise premise = premiseService.getPremise(idPremise);

        String FILE_URI = "persons/agent/"
                + agent.getEmail() + "/properties/houses/"
                + "premise_"+ premise.getId()  + "/pictures/";
        String fileUrl = "";

        try{
            File file = convertMultiPartToFile(multipartFile);
            String filePath = FILE_URI + validateFormat(generateFileName(multipartFile));
            fileUrl = createUrl(filePath);
            uploadFileToS3Bucket(filePath, file);

            CreateImageRequest request = CreateImageRequest.builder().premise(premise).url(fileUrl).build();
            imageService.saveHouseImage(request);

        } catch (Exception e){
            e.printStackTrace();
        }

        UploadImageResponse imageResponse = UploadImageResponse.builder().url(fileUrl).build();
        return BaseResponse.builder()
                .data(imageResponse)
                .success(true)
                .httpStatus(HttpStatus.CREATED)
                .message("The image was uploaded").build();
    }

    private String createUrl(@Valid String filePath){
        return  "https://" + BUCKET_NAME + "." + ENDPOINT_URL + filePath;
    }

    private String validateFormat(String fileName){
        String Ext = FilenameUtils.getExtension(fileName);
        if(!Ext.equals("png")){
            throw new NotValidFormatException(".png");
        }
        return fileName;
    }

    @PostConstruct
    private void initializeAmazon(){
        AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_2)
                .build();
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private  String generateFileName(MultipartFile multipart){
        return multipart.getOriginalFilename().replace(" ","_");
    }

    private void uploadFileToS3Bucket(String fileName, File file){
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead);
        s3client.putObject(putObjectRequest);
    }

}
