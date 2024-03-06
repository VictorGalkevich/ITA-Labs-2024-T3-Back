package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
