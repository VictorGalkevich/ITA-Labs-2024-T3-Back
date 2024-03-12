package com.ventionteams.applicationexchange.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
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

    public byte[] download(String fileName) {
        try {
            byte[] content;
            final S3Object s3Object = amazonS3.getObject(configProperties.getBucketName(), fileName);
            final S3ObjectInputStream stream = s3Object.getObjectContent();
            content = IOUtils.toByteArray(stream);
            s3Object.close();
            return content;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (AmazonServiceException serviceException) {
            serviceException.printStackTrace();
        } catch (AmazonClientException clientException) {
            clientException.printStackTrace();
        }
        return null;
    }
}
