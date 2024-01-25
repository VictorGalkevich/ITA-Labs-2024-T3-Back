package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.entity.Subcategory;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SubcategoryRepository {
    private final HashSet<Subcategory> subcategories = new HashSet<>();

    public List<Subcategory> findAllByCategoryId(Integer categoryId) {
        return subcategories.stream().filter(x -> Objects.equals(x.getCategoryId(), categoryId)).toList();
    }

    public void deleteAll(List<Subcategory> subcategories) {
        subcategories.forEach(this.subcategories::remove);
    }

    public void addAll(List<Subcategory> subcategories) {

        this.subcategories.addAll(subcategories);
    }

    public void update(List<Subcategory> delete, List<Subcategory> add) {
        deleteAll(delete);
        addAll(add);
    }
}
