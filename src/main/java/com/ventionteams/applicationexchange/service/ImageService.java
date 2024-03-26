package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.config.ConfigProperties;
import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.entity.Image;
import com.ventionteams.applicationexchange.entity.Lot;
import com.ventionteams.applicationexchange.mapper.LotMapper;
import com.ventionteams.applicationexchange.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.waiters.S3Waiter;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class ImageService {
    private final LotMapper lotMapper;
    private final ImageRepository imageRepository;
    private final StorageService storageService;
    private final S3Client s3Client;
    private final ConfigProperties configProperties;

    public LotReadDTO saveListImagesForLot(List<MultipartFile> images, LotReadDTO lot) {
        boolean isMainImage = true;
        for (MultipartFile file : images) {

            String name = String.format("%s/%s", "lot", RandomStringUtils.randomAlphanumeric(12));
            Image image = Image.builder()
                    .name(name)
                    .lot(lotMapper.toLot(lot))
                    .url(storageService.upload(file, name))
                    .isMainImage(isMainImage)
                    .build();

            isMainImage = false;

            S3Waiter waiter = s3Client.waiter();
            HeadObjectRequest waitRequest = HeadObjectRequest.builder()
                    .bucket(configProperties.getBucketName())
                    .key(name)
                    .build();

            WaiterResponse<HeadObjectResponse> waitResponse = waiter.waitUntilObjectExists(waitRequest);

            waitResponse.matched().response().ifPresent(x -> {
                lot.getImages().add(imageRepository.save(image));
            });

        }
        return lot;
    }

    public Long saveSingleImage(MultipartFile file, String folder) {
        if (file == null) return null;
        String name = String.format("%s/%s", folder, RandomStringUtils.randomAlphanumeric(12));
        Image image = Image.builder()
                .name(name)
                .url(storageService.upload(file, name))
                .build();

        return imageRepository.save(image).getId();
    }

    public Optional<Image> getAvatar(Long id) {
        return imageRepository.findById(id);
    }

    public void deleteAvatar(Long id) {
        if (id == null) return;
        Image image = imageRepository.findById(id).get();

        storageService.delete(image.getName());
        imageRepository.delete(image);
        log.info("Delete image with id: {}", id);
    }

    public void updateListImagesForLot(List<MultipartFile> newListImages, Lot lot) {

    }
}
