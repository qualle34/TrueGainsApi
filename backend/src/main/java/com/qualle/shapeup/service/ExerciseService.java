package com.qualle.shapeup.service;

import com.qualle.shapeup.api.CategoryDto;
import com.qualle.shapeup.api.ExerciseDto;

import java.util.List;

public interface ExerciseService {

    List<CategoryDto> getCategories();

    List<ExerciseDto> getExercisesByCategory(long categoryId);

    List<ExerciseDto> getExercise(long id);
}
