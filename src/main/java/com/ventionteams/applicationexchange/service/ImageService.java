package com.ventionteams.applicationexchange.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ventionteams.applicationexchange.config.AWSClientConfig;
import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.entity.Image;
import com.ventionteams.applicationexchange.mapper.LotMapper;
import com.ventionteams.applicationexchange.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final AmazonS3 amazonS3;
    private final AWSClientConfig awsClientConfig;
    private final LotMapper lotMapper;
    private final ImageRepository imageRepository;

    public LotReadDTO saveListImages(List<MultipartFile> files, LotReadDTO lot) {
        for (MultipartFile file : files) {
            Image image = Image.builder()
                    .name(upload(file))
                    .lot(lotMapper.toLot(lot))
                    .build();
            lot.images().add(imageRepository.save(image));
        }
        return lot;
    }

    public String upload(MultipartFile file) {
        File localFile = convertNultiPartFileToFile(file);
        amazonS3.putObject(new PutObjectRequest(awsClientConfig.getBucketName(), file.getOriginalFilename(), localFile));
        return file.getOriginalFilename();
    }

    private File convertNultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try {
            Files.copy(file.getInputStream(), convertedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return convertedFile;
    }
}
