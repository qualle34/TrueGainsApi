package com.qualle.truegain.service.impl;

import com.qualle.truegain.api.CategoryDto;
import com.qualle.truegain.api.ExerciseDto;
import com.qualle.truegain.api.SimpleExerciseDto;
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
import com.qualle.truegain.service.util.DateFormatUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
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
        return exerciseMapper.toDto(exerciseRepository.findByIdWithImage(id), List.of("image"));
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
    public List<SimpleExerciseDto> getFrequentlyUsedExercises(long userId, int count) {
        List<Map<String, Object>> exercises = exerciseRepository.findFrequentlyUsedExercises(userId);

        List<SimpleExerciseDto> result = new ArrayList<>();


        int i = 1;
        for (Map<String, Object> map : exercises) {

            if (i == count) {
                break;
            }

            long recordCount = (long) map.get("count");
            Exercise exercise = (Exercise) map.get("exercise");

            SimpleExerciseDto dto = SimpleExerciseDto.builder()
                    .id(exercise.getId())
                    .name(exercise.getName())
                    .equipment(exercise.getEquipment())
                    .recordCount((int) recordCount)
                    .build();

            result.add(dto);
            i++;
        }

        return result;
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

        float maxRep = 0;
        float maxLoad = 0;
        float totalLoad = 0;

        for (Map<String, Object> map : records) {

            LocalDateTime date = (LocalDateTime) map.get("date");
            Record record = (Record) map.get("record");

            float dayNum = DateFormatUtil.getDayNumber(date);
            float load = record.getWeight() * record.getReps();

            if (load > maxLoad) {
                maxLoad = load;
            }

            totalLoad += load;

            loadData.put(dayNum, load);

            if (record.getReps() == 1) {
                maxRepData.put(dayNum, record.getWeight());

                if (record.getWeight() > maxRep) {
                    maxRep = record.getWeight();
                }
            }
        }

        dto.setLoadData(loadData);
        dto.setMaxRepData(maxRepData);

        dto.setSummary("1 Rep max: " + (maxRep == 0 ? " - " : maxRep + " Kg") + "\nMax load: " + maxLoad + " Kg\nTotal load: " + totalLoad + " Kg");

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
