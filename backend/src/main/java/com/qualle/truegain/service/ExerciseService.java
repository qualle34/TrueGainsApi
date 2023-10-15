package com.qualle.truegain.service;

import com.qualle.truegain.api.CategoryDto;
import com.qualle.truegain.api.ExerciseDto;

import java.util.List;

public interface ExerciseService {

    List<CategoryDto> getCategories();

    List<ExerciseDto> getExercisesByCategory(long categoryId);

    List<ExerciseDto> getExercise(long id);
}
