package com.qualle.truegain.service.mapper.impl;

import com.qualle.truegain.api.ExerciseDto;
import com.qualle.truegain.api.WorkoutDto;
import com.qualle.truegain.model.entity.Exercise;
import com.qualle.truegain.model.entity.Record;
import com.qualle.truegain.model.entity.User;
import com.qualle.truegain.model.entity.Workout;
import com.qualle.truegain.service.mapper.ExerciseMapper;
import com.qualle.truegain.service.mapper.RecordMapper;
import com.qualle.truegain.service.mapper.WorkoutMapper;
import com.qualle.truegain.service.util.DateFormatUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WorkoutMapperImpl implements WorkoutMapper {

    private final RecordMapper recordMapper;
    private final ExerciseMapper exerciseMapper;

    @Override
    public WorkoutDto toDto(Workout workout, List<String> params) {

        validate(workout);

        long userId = 0;

        if (workout.getUser() != null) {
            userId = workout.getUser().getId();
        }

        WorkoutDto dto = WorkoutDto.builder()
                .id(workout.getId())
                .userId(userId)
                .date(DateFormatUtil.toString(workout.getDate()))
                .build();

        List<ExerciseDto> exerciseDtoList = new ArrayList<>();


        if (params.contains("records") && workout.getRecords() != null) {

            Map<Exercise, List<Record>> exercises = workout.getRecords().stream()
                    .collect(Collectors.groupingBy(Record::getExercise));

            for (Map.Entry<Exercise, List<Record>> entry : exercises.entrySet()) {

                Exercise exercise = entry.getKey();
                exercise.setRecords(entry.getValue());

                exerciseDtoList.add(exerciseMapper.toDto(exercise, List.of("records", "image")));
            }
        }

        dto.setExercises(exerciseDtoList);

        return dto;
    }

    @Override
    public Workout fromDto(WorkoutDto dto, List<String> params) {

        validate(dto);

        User user = User.builder().id(dto.getUserId()).build();

        Workout workout = Workout.builder()
                .id(dto.getId())
                .date(DateFormatUtil.toDate(dto.getDate()))
                .user(user)
                .build();

        List<Record> records = new ArrayList<>();

        if (params.contains("records") && dto.getExercises() != null) {

            for (ExerciseDto exerciseDto : dto.getExercises()) {

                Exercise exercise = Exercise.builder()
                        .id(exerciseDto.getId())
                        .records(new ArrayList<>())
                        .build();

                records.addAll(exerciseDto.getRecords().stream().map(r -> {

                    Record record = recordMapper.fromDto(r);
                    record.setExercise(exercise);
                    record.setWorkout(workout);
                    exercise.getRecords().add(record);

                    return record;
                }).toList());
            }

        }

        workout.setRecords(records);

        return workout;
    }
}
