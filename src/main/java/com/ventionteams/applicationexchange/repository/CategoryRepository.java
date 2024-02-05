package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByParentIdIsNull();
}
