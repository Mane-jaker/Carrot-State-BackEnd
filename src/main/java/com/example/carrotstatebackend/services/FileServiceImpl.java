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
import com.example.carrotstatebackend.controllers.dtos.response.GetRealStateResponse;
import com.example.carrotstatebackend.controllers.dtos.response.UploadImageResponse;
import com.example.carrotstatebackend.controllers.exceptions.NotValidFormatException;
import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.Plot;
import com.example.carrotstatebackend.entities.Premise;
import com.example.carrotstatebackend.services.interfaces.*;
import com.sun.istack.Nullable;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private IRealStateService managerService;

    @Autowired
    private IHouseService houseService;

    @Autowired
    private IPlotService plotService;

    @Autowired
    private IPremiseService premiseService;

    @Autowired
    private IAgentService agentService;

    @Autowired
    private IImageHouseService imageHouseService;

    @Autowired
    private IImagePremiseService imagePremiseService;

    @Autowired
    private IImagePlotService imagePlotService;

    private AmazonS3 s3client;
    private String ENDPOINT_URL = "s3.us-east-2.amazonaws.com";
    private String BUCKET_NAME = "conejobucket";
    private final String ACCESS_KEY = "AKIAQUVISYWUMTPJ5WZP";
    private final String SECRET_KEY = "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";

    @Override
    public BaseResponse list() {
        return BaseResponse.builder()
                .data(imageHouseService.list())
                .message("list")
                .success(true)
                .httpStatus(HttpStatus.FOUND).build();
    }

    @Override
    public BaseResponse uploadRealStateProfilePicture(MultipartFile multipartFile, Long id) {
        GetRealStateResponse realState = managerService.getResponse(id);
        String FILE_URI = "persons/manager/" + realState.getEmail() + "/profile_picture/";
        String fileUrl = "";
        try{
            File file = convertMultiPartToFile(multipartFile);
            String filePath = FILE_URI + generateFileName(multipartFile);
            fileUrl = createUrl(filePath);
            uploadFileToS3Bucket(filePath, file);
            managerService.updateManagerProfile(fileUrl, id);
            file.delete();
        } catch (Exception e){
            e.printStackTrace();
        }
        return BaseResponse.builder()
                .data(UploadImageResponse.builder().url(fileUrl).build())
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
            String filePath = FILE_URI + generateFileName(multipartFile);
            fileUrl = createUrl(filePath);
            uploadFileToS3Bucket(filePath, file);
            agentService.updateAgentProfile(fileUrl, id);
            file.delete();
        } catch (Exception e){
            e.printStackTrace();
        }
        return BaseResponse.builder()
                .data(UploadImageResponse.builder().url(fileUrl).build())
                .success(true)
                .httpStatus(HttpStatus.CREATED)
                .message("The image was uploaded").build();
    }

    @Override
    public BaseResponse uploadHousePicture(MultipartFile multipartFile, Long idHouse) {
        House house = houseService.getHouse(idHouse);
        String FILE_URI = generateURI(house);
        String fileUrl = "";
        try{
            File file = convertMultiPartToFile(multipartFile);
            String filePath = FILE_URI + generateFileName(multipartFile);
            fileUrl = createUrl(filePath);
            uploadFileToS3Bucket(filePath, file);
            imageHouseService.saveImage(from(house, fileUrl));
            file.delete();
        } catch (Exception e){
            e.printStackTrace();
        }
        return BaseResponse.builder()
                .data(UploadImageResponse.builder().url(fileUrl).build())
                .success(true)
                .httpStatus(HttpStatus.CREATED)
                .message("The image was uploaded").build();
    }

    @Override
    public BaseResponse uploadPlotPicture(MultipartFile multipartFile,  Long idPlot) {

        Plot plot = plotService.getPlot(idPlot);
        String FILE_URI = generateURI(plot);
        String fileUrl = "";

        try{
            File file = convertMultiPartToFile(multipartFile);
            String filePath = FILE_URI + generateFileName(multipartFile);
            fileUrl = createUrl(filePath);
            uploadFileToS3Bucket(filePath, file);
            imagePlotService.saveImage(from(plot, fileUrl));
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
    public BaseResponse uploadPremisePicture(MultipartFile multipartFile,  Long idPremise) {

        Premise premise = premiseService.getPremise(idPremise);
        String FILE_URI = generateURI(premise);
        String fileUrl = "";

        try{
            File file = convertMultiPartToFile(multipartFile);
            String filePath = FILE_URI + generateFileName(multipartFile);
            fileUrl = createUrl(filePath);
            uploadFileToS3Bucket(filePath, file);
            file.delete();
            imagePremiseService.saveImage(from(premise, fileUrl));
        } catch (Exception e){
            e.printStackTrace();
        }
        return BaseResponse.builder()
                .data(UploadImageResponse.builder().url(fileUrl).build())
                .success(true)
                .httpStatus(HttpStatus.CREATED)
                .message("The image was uploaded").build();
    }

    private CreateImageRequest from(House house, String url){
        return CreateImageRequest.builder().house(house).url(url).build();
    }

    private CreateImageRequest from(Plot plot, String url){
        return CreateImageRequest.builder().plot(plot).url(url).build();
    }

    private CreateImageRequest from(Premise premise, String url){
        return CreateImageRequest.builder().premise(premise).url(url).build();
    }

    private String createUrl(@Valid String filePath){
        return  "https://" + BUCKET_NAME + "." + ENDPOINT_URL + "/" + filePath;
    }

    private String generateURI(Premise premise){
        return "persons/agent/" + premise.getAgent().getEmail()
                + "/properties/premise/" + "premise_" + premise.getId() + "/pictures/";
    }

    private String generateURI(Plot plot){
        return "persons/agent/" + plot.getAgent().getEmail()
                + "/properties/plot/" + "plot_" + plot.getId() + "/pictures/";
    }

    private String generateURI(House house){
        return "persons/agent/" + house.getAgent().getEmail()
                + "/properties/houses/" + "house_" + house.getId() + "/pictures/";
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
