package com.qualle.shapeup.service.mapper.impl;

import com.qualle.shapeup.api.CategoryDto;
import com.qualle.shapeup.model.entity.Category;
import com.qualle.shapeup.service.mapper.CategoryMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements CategoryMapper {
    @Override
    public CategoryDto toDto(Category category) {
        return null;
    }

    @Override
    public Category fromDto(CategoryDto categoryDto) {
        return null;
    }
}
