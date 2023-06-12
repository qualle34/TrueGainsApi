package com.qualle.shapeup.service.impl;

import com.qualle.shapeup.api.CategoryDto;
import com.qualle.shapeup.api.ExerciseDto;
import com.qualle.shapeup.repository.CategoryRepository;
import com.qualle.shapeup.repository.ExerciseRepository;
import com.qualle.shapeup.service.ExerciseService;
import com.qualle.shapeup.service.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getCategories() {
        List<CategoryDto> result = new ArrayList<>();

        categoryRepository.findAll()
                .forEach(c -> result.add(categoryMapper.toDto(c)));

        return result;
    }

    @Override
    public List<ExerciseDto> getExercisesByCategory(long categoryId) {
        return null;
    }

    @Override
    public List<ExerciseDto> getExercise(long id) {
        return null;
    }
}
