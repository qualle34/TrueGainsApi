package com.qualle.truegain.service.mapper.impl;

import com.qualle.truegain.api.CategoryDto;
import com.qualle.truegain.service.mapper.CategoryMapper;
import com.qualle.truegain.model.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDto toDto(Category category) {

        validate(category);

        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .imageLink(category.getImage().getLink())
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
