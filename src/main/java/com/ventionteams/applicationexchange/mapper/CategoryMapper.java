package com.ventionteams.applicationexchange.mapper;

import com.ventionteams.applicationexchange.config.MapperConfiguration;
import com.ventionteams.applicationexchange.dto.CategoryCreateEditDto;
import com.ventionteams.applicationexchange.dto.CategoryReadDto;
import com.ventionteams.applicationexchange.entity.Category;
import com.ventionteams.applicationexchange.repository.CategoryRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(config = MapperConfiguration.class)
public abstract class CategoryMapper {
    @Autowired
    protected CategoryRepository categoryRepository;
    @Mapping(target = "id", ignore = true)
    public abstract Category toCategory(CategoryCreateEditDto dto);

    @Mapping(target = "parentId", source = "parent.id")
    @Mapping(target = "subcategories", expression = "java(toDtoList(categoryRepository.findAllByParentId(category.getId())))")
    public abstract CategoryReadDto toReadDto(Category category);

    public abstract List<CategoryReadDto> toDtoList(List<Category> list);

    public abstract void map(@MappingTarget Category to, CategoryCreateEditDto from);
}
