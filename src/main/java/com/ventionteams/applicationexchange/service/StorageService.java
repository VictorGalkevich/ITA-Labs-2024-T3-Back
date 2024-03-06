package com.ventionteams.applicationexchange.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ventionteams.applicationexchange.config.ConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final AmazonS3 amazonS3;
    private final ConfigProperties configProperties;

    public Boolean upload(MultipartFile multipartFile, String name) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        try {
            amazonS3.putObject(new PutObjectRequest(configProperties.getBucketName(), name, multipartFile.getInputStream(), metadata));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return amazonS3.doesObjectExist(configProperties.getBucketName(), name);
    }
}
