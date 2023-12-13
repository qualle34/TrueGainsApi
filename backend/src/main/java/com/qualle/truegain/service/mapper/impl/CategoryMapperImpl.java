package com.qualle.truegain.service.mapper.impl;

import com.qualle.truegain.api.CategoryDto;
import com.qualle.truegain.service.mapper.CategoryMapper;
import com.qualle.truegain.model.entity.Category;
import com.qualle.truegain.service.mapper.ExerciseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryMapperImpl implements CategoryMapper {

    private final ExerciseMapper exerciseMapper;

    @Override
    public CategoryDto toDto(Category category, List<String> params) {

        validate(category);

        CategoryDto dto = CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();

        if (params.contains("image") && category.getImage() != null) {
            dto.setImageLink(category.getImage().getLink());
        }

        if (params.contains("exercises") && category.getExercises() != null) {

            List<String> exercisesParams = new ArrayList<>();

            if (params.contains("exercises-image")) {
                exercisesParams.add("image");
            }

            dto.setExercises(exerciseMapper.toDto(category.getExercises(), exercisesParams));
        }

        return dto;
    }

    @Override
    public Category fromDto(CategoryDto dto, List<String> params) {

        validate(dto);

        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
