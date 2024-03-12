package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.entity.Image;
import com.ventionteams.applicationexchange.mapper.LotMapper;
import com.ventionteams.applicationexchange.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final LotMapper lotMapper;
    private final ImageRepository imageRepository;
    private final StorageService storageService;

    public LotReadDTO saveListImages(List<MultipartFile> files, LotReadDTO lot) {
        for (MultipartFile file : files) {
            String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(12), file.getName());
            Image image = null;
            try {
                image = Image.builder()
                        .name(name)
                        .lot(lotMapper.toLot(lot))
                        .bytes(file.getBytes())
                        .build();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (storageService.upload(file, name)) {
                lot.getImages().add(imageRepository.save(image));
            }
        }
        return lot;
    }

    public Optional<LotReadDTO> getListImages(Optional<LotReadDTO> lot) {
        for (Image image : lot.get().getImages()) {
            image.setBytes(storageService.download(image.getName()));
        }
        return lot;
    }
}
