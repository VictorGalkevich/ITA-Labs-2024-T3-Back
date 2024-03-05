package com.ventionteams.applicationexchange.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ventionteams.applicationexchange.config.ConfigProperties;
import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.entity.Image;
import com.ventionteams.applicationexchange.mapper.LotMapper;
import com.ventionteams.applicationexchange.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final AmazonS3 amazonS3;
    private final ConfigProperties configProperties;
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

    public String upload(MultipartFile multipartFile) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        try {
            amazonS3.putObject(new PutObjectRequest(configProperties.getBucketName(), configProperties.getAccessKey(), multipartFile.getInputStream(), metadata));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return multipartFile.getOriginalFilename(); // need to return key name from s3 or change key by myself
    }
}
