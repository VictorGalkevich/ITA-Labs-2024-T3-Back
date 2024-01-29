package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.container.JsonContainer;
import com.ventionteams.applicationexchange.dto.CategoryCreateEditDto;
import com.ventionteams.applicationexchange.entity.Category;
import com.ventionteams.applicationexchange.mapper.CategoryMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {
    private final TreeMap<Integer, Category> categories = new TreeMap<>();
    private final JsonContainer jsonContainer;
    private final CategoryMapper mapper;

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
        for (CategoryCreateEditDto category : jsonContainer.categories()) {
            Category val = mapper.toCategory(category);
            categories.put(val.getId(), val);
        }
    }
}
