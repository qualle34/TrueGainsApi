package com.truegain.service.mapper.impl;

import com.truegain.api.CategoryDto;
import com.truegain.model.entity.Category;
import com.truegain.service.mapper.CategoryMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDto toDto(Category category) {

        validate(category);

        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    @Override
    public Category fromDto(CategoryDto dto) {

        validate(dto);

        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
