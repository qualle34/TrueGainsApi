package com.truegain.service;

import com.truegain.api.CategoryDto;
import com.truegain.api.ExerciseDto;

import java.util.List;

public interface ExerciseService {

    List<CategoryDto> getCategories();

    List<ExerciseDto> getExercisesByCategory(long categoryId);

    List<ExerciseDto> getExercise(long id);
}
