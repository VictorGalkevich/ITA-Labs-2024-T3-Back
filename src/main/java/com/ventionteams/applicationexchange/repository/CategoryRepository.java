package com.ventionteams.applicationexchange.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ventionteams.applicationexchange.container.JsonContainer;
import com.ventionteams.applicationexchange.dto.CategoryCreateEditDto;
import com.ventionteams.applicationexchange.dto.CategoryReadDto;
import com.ventionteams.applicationexchange.entity.Category;
import com.ventionteams.applicationexchange.mapper.CategoryCreateEditMapper;
import com.ventionteams.applicationexchange.mapper.CategoryReadMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {
    private final TreeMap<Integer, Category> categories = new TreeMap<>();
    private final ObjectMapper objectMapper;
    private final CategoryCreateEditMapper mapper;

    public Optional<Category> findById(Integer id) {
        return Optional.ofNullable(categories.get(id));
    }

    public List<Category> findAll() {
        return categories.values().stream().toList();
    }

    public void delete(Category category) {
        categories.remove(category.getId());
    }

    public Category save(Category category) {
        categories.put(category.getId(), category);
        return category;
    }

    public Category update(Category category) {
        categories.replace(category.getId(), category);
        return categories.get(category.getId());
    }

    @SneakyThrows
    @PostConstruct
    private void init() {
        File file = new File("mock-entities.json");
        JsonContainer jsonContainer = objectMapper.readValue(file, JsonContainer.class);
        for (CategoryCreateEditDto category : jsonContainer.categories()) {
            Category val = mapper.map(category);
            categories.put(val.getId(), val);
        }
    }
}
