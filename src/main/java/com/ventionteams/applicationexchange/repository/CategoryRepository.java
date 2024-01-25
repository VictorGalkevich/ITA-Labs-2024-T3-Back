package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {
    private final TreeMap<Integer, Category> categories = new TreeMap<>();
    private final SubcategoryRepository subcategoryRepository;

    public Optional<Category> findById(Integer id) {
        return Optional.ofNullable(categories.get(id));
    }

    public List<Category> findAll() {
        return categories.values().stream().toList();
    }

    public void delete(Category category) {
        categories.remove(category.getId());
        subcategoryRepository.deleteAll(category.getSubcategories());
    }

    public Category save(Category category) {
        categories.put(category.getId(), category);
        subcategoryRepository.addAll(category.getSubcategories());
        return category;
    }

    public Category update(Category category) {
        subcategoryRepository.update(categories.get(category.getId()).getSubcategories(), category.getSubcategories());
        return categories.replace(category.getId(), category);
    }
}
