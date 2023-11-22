package com.qualle.truegain.service;

import com.qualle.truegain.api.CategoryDto;
import com.qualle.truegain.api.ExerciseDto;

import java.util.List;

public interface ExerciseService {

    List<CategoryDto> getCategories();

    List<CategoryDto> getCategoriesWithExercises();

    List<ExerciseDto> getExercisesByCategory(long categoryId);

    ExerciseDto getExercise(long id);

    ExerciseDto getExerciseWithRecordsByIdForUserId(long id, long userId);

    List<ExerciseDto> getExerciseWithRecordsByWorkoutId(long workoutId);

    ExerciseDto getExerciseWithChartsByUserId(long id, long userId);
}
