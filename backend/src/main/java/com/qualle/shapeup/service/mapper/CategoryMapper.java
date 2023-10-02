package com.qualle.shapeup.service.mapper;

import com.qualle.shapeup.api.CategoryDto;
import com.qualle.shapeup.model.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category fromDto(CategoryDto dto);

    CategoryDto toDto(Category category);
}