package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findById(Long id);
    Optional<Image> findByUrl(String url);

    List<Image> findByLotId(Long id);
}
