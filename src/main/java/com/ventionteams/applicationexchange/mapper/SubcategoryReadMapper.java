package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.dto.SubcategoryReadDto;
import com.ventionteams.applicationexchange.entity.Subcategory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SubcategoryReadMapper implements Mapper<Subcategory, SubcategoryReadDto> {
    @Override
    public SubcategoryReadDto map(Subcategory object) {
        return new SubcategoryReadDto(
                object.getId(),
                object.getCategoryId(),
                object.getName()
        );
    }
}
