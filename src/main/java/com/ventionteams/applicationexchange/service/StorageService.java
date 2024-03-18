package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.config.ConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageService {
    private final S3Client s3Client;
    private final ConfigProperties configProperties;

    public String upload(MultipartFile multipartFile, String name) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(configProperties.getBucketName())
                .key(name)
                .contentType(multipartFile.getContentType())
                .acl("public-read")
                .build();

        try {
            s3Client.putObject(request, RequestBody.fromBytes(multipartFile.getBytes()));
        } catch (IOException e) {
            log.info("Error saving file to s3. Filename: {}", name);
            throw new RuntimeException(e);
        }
        return getURL(name);
    }

    private String getURL(String keyName) {
        try {
            GetUrlRequest request = GetUrlRequest.builder()
                    .bucket(configProperties.getBucketName())
                    .key(keyName)
                    .build();

            return s3Client.utilities().getUrl(request).toExternalForm();

        } catch (S3Exception e) {
            log.info("Error getting file's url. Filename: {}. Error: {}", keyName, e.awsErrorDetails().errorMessage());
        }
        return null;
    }
}
