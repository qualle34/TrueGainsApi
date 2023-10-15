package com.truegain.service.impl;

import com.truegain.api.CategoryDto;
import com.truegain.api.ExerciseDto;
import com.truegain.repository.CategoryRepository;
import com.truegain.repository.ExerciseRepository;
import com.truegain.service.ExerciseService;
import com.truegain.service.mapper.CategoryMapper;
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
