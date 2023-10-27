package com.qualle.truegain.service.impl;

import com.qualle.truegain.api.CategoryDto;
import com.qualle.truegain.api.ExerciseDto;
import com.qualle.truegain.model.entity.Exercise;
import com.qualle.truegain.repository.CategoryRepository;
import com.qualle.truegain.repository.ExerciseRepository;
import com.qualle.truegain.service.ExerciseService;
import com.qualle.truegain.service.mapper.CategoryMapper;
import com.qualle.truegain.service.mapper.ExerciseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final CategoryRepository categoryRepository;
    private final ExerciseMapper exerciseMapper;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getCategories() {
        return categoryMapper.toDto(categoryRepository.findAllWithImage());
    }

    @Override
    public List<ExerciseDto> getExercisesByCategory(long categoryId) {
        List<Exercise> exercises = exerciseRepository.findAllExercisesByCategoryId(categoryId);

        return exerciseMapper.toDto(exercises);
    }

    @Override
    public List<ExerciseDto> getExercise(long id) {
        return null;
    }

    @Override
    public List<ExerciseDto> getExerciseWithRecordsByWorkoutId(long workoutId) {
        return exerciseMapper.toDto(exerciseRepository.findAllExercisesWithRecordsByWorkoutId(workoutId));
    }
}
