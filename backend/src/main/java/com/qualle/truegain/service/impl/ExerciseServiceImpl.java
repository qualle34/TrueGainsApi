package com.qualle.truegain.service.impl;

import com.qualle.truegain.api.CategoryDto;
import com.qualle.truegain.api.ExerciseDto;
import com.qualle.truegain.api.WorkoutDto;
import com.qualle.truegain.model.entity.Exercise;
import com.qualle.truegain.model.entity.Workout;
import com.qualle.truegain.model.exception.EntityNotFoundException;
import com.qualle.truegain.repository.CategoryRepository;
import com.qualle.truegain.repository.ExerciseRepository;
import com.qualle.truegain.service.ExerciseService;
import com.qualle.truegain.service.basic.AbstractService;
import com.qualle.truegain.service.mapper.CategoryMapper;
import com.qualle.truegain.service.mapper.ExerciseMapper;
import com.qualle.truegain.service.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl extends AbstractService<Exercise, ExerciseDto, Long>  implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final CategoryRepository categoryRepository;
    private final ExerciseMapper exerciseMapper;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getCategories() {
        return categoryMapper.toDto(categoryRepository.findAllWithImage(), List.of("image"));
    }

    @Override
    public List<CategoryDto> getCategoriesWithExercises() {
        return categoryMapper.toDto(categoryRepository.findAllWithExerciseAndImage(), List.of("image", "exercises", "exercises-image"));
    }

    @Override
    public List<ExerciseDto> getExercisesByCategory(long categoryId) {
        List<Exercise> exercises = exerciseRepository.findAllExercisesByCategoryId(categoryId);

        return exerciseMapper.toDto(exercises);
    }

    @Override
    public ExerciseDto getExercise(long id) {
        return exerciseMapper.toDto(exerciseRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public ExerciseDto getExerciseWithRecordsByIdForUserId(long id, long userId) {
        Exercise exercise = exerciseRepository.findByIdAndUserIdWithRecords(id, userId);

        if (exercise != null) {
            return exerciseMapper.toDto(exercise, List.of("image", "records"));
        }

        return getExercise(id);
    }

    @Override
    public List<ExerciseDto> getExerciseWithRecordsByWorkoutId(long workoutId) {
        return exerciseMapper.toDto(exerciseRepository.findAllExercisesWithRecordsByWorkoutId(workoutId));
    }


    @Override
    protected CrudRepository<Exercise, Long> getRepository() {
        return exerciseRepository;
    }

    @Override
    protected GenericMapper<Exercise, ExerciseDto> getMapper() {
        return exerciseMapper;
    }
}
