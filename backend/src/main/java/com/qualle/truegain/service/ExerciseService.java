package com.qualle.truegain.service;

import com.qualle.truegain.api.CategoryDto;
import com.qualle.truegain.api.ExerciseDto;
import com.qualle.truegain.model.entity.Exercise;

import java.util.List;

public interface ExerciseService {

    List<CategoryDto> getCategories();

    List<ExerciseDto> getExercisesByCategory(long categoryId);

    List<ExerciseDto> getExercise(long id);

    List<ExerciseDto> getExerciseWithRecordsByWorkoutId(long workoutId);
}
