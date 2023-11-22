package com.qualle.truegain.service.impl;

import com.qualle.truegain.api.CategoryDto;
import com.qualle.truegain.api.ExerciseDto;
import com.qualle.truegain.model.entity.Exercise;
import com.qualle.truegain.model.entity.Record;
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

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl extends AbstractService<Exercise, ExerciseDto, Long> implements ExerciseService {

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
    public ExerciseDto getExerciseWithChartsByUserId(long id, long userId) {

        Exercise exercise = exerciseRepository.findByIdWithImage(id);

        ExerciseDto dto = exerciseMapper.toDto(exercise, List.of("image"));

        List<Map<String, Object>> records = exerciseRepository.findRecordsByUserAndExerciseGroupByDate(id, userId);

        Map<Float, Float> maxRepData = new HashMap<>();
        Map<Float, Float> loadData = new HashMap<>();

        for (Map<String, Object> map : records) {

            LocalDateTime date = (LocalDateTime) map.get("date");
            Record record = (Record) map.get("record");

            float dayNum = (float) date.toInstant(ZoneOffset.UTC).getEpochSecond() / 86400;

            loadData.put(dayNum, record.getWeight() * record.getReps());

            if (record.getReps() == 1) {
                maxRepData.put(dayNum, record.getWeight());
            }

        }

        dto.setLoadData(loadData);
        dto.setMaxRepData(maxRepData);

        dto.setImageLink(dto.getImageLink() + ".png");

        return dto;
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
