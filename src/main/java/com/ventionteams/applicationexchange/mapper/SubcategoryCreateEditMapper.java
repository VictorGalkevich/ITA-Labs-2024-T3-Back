package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.dto.SubcategoryCreateEditDto;
import com.ventionteams.applicationexchange.entity.Subcategory;
import org.springframework.stereotype.Component;

@Component
public class SubcategoryCreateEditMapper implements Mapper<SubcategoryCreateEditDto, Subcategory> {

    @Override
    public Subcategory map(SubcategoryCreateEditDto object) {
        return new Subcategory(
                object.getId(),
                object.getCategoryId(),
                object.getName()
        );
    }
}
