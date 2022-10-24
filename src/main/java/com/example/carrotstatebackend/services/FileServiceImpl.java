
package com.example.carrotstatebackend.services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetAgentResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetManagerResponse;
import com.example.carrotstatebackend.services.interfaces.IAgentService;
import com.example.carrotstatebackend.services.interfaces.IFileService;
import com.example.carrotstatebackend.services.interfaces.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    IManagerService managerService;
    IAgentService agentService;

    private AmazonS3 s3client;
    private String ENDPOINT_URL = "s3.us-east-2.amazonaws.com";
    private String BUCKET_NAME = "realstatebucket01";
    private final String ACCESS_KEY = "AKIA57FUC7G7ZD63OHNA";
    private final String SECRET_KEY = "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";

    @Override
    public String uploadManagerProfilePicture(MultipartFile multipartFile, Long id) {

        GetManagerResponse manager = managerService.get(id);
        String FILE_URI = "persons/manager/" + manager.getMail() + "/profile_picture/";
        String fileUrl = "";

        try{

            File file = convertMultiPartToFile(multipartFile);
            String filePath = FILE_URI + generateFileName(multipartFile);

            fileUrl = "https://" + BUCKET_NAME + "." + ENDPOINT_URL + "/" + filePath;
            uploadFileToS3Bucket(filePath, file);

            managerService.updateManagerProfile(fileUrl, id);
            file.delete();

        } catch (Exception e){
            e.printStackTrace();
        }
        return fileUrl;
    }

    @Override
    public String uploadAgentProfilePicture(MultipartFile multipartFile, Long id) {

        GetAgentResponse agent = agentService.get(id);
        String FILE_URI = "persons/agent/" + agent.getMail() + "/profile_picture/";
        String fileUrl = "";

        try{

            File file = convertMultiPartToFile(multipartFile);
            String filePath = FILE_URI + generateFileName(multipartFile);

            fileUrl = "https://" + BUCKET_NAME + "." + ENDPOINT_URL + filePath;
            uploadFileToS3Bucket(filePath, file);

            //agentService.udateAgentProfile(fileUrl, id);
            file.delete();

        } catch (Exception e){
            e.printStackTrace();
        }
        return fileUrl;
    }

    @Override
    public String uploadHousePicture(List<MultipartFile> multipartFile, Long id) {
        return null;
    }

    @Override
    public String uploadPlotPicture(List<MultipartFile> multipartFile,  Long id) {
        return null;
    }

    @Override
    public String uploadPremisePicture(List<MultipartFile> multipartFile,  Long id) {
        return null;
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
