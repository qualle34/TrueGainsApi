package com.truegain.service.mapper.impl;

import com.truegain.api.CategoryDto;
import com.truegain.model.entity.Category;
import com.truegain.service.mapper.CategoryMapper;
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
