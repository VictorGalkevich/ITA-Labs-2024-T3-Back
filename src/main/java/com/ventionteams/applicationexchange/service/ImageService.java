package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.entity.Image;
import com.ventionteams.applicationexchange.mapper.LotMapper;
import com.ventionteams.applicationexchange.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final LotMapper lotMapper;
    private final ImageRepository imageRepository;
    private final StorageService storageService;

    @Transactional
    public LotReadDTO saveListImages(List<MultipartFile> files, LotReadDTO lot) {
        for (MultipartFile file : files) {
            String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(12), file.getName());
            Image image = Image.builder()
                    .name(name)
                    .lot(lotMapper.toLot(lot))
                    .build();
            if (storageService.upload(file, name)) {
                lot.images().add(imageRepository.save(image));
            }
        }
        return lot;
    }
}
