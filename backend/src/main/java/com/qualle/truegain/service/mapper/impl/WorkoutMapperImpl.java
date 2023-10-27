package com.qualle.truegain.service.mapper.impl;

import com.qualle.truegain.api.*;
import com.qualle.truegain.model.entity.Exercise;
import com.qualle.truegain.service.mapper.ExerciseMapper;
import com.qualle.truegain.service.mapper.RecordMapper;
import com.qualle.truegain.service.mapper.WorkoutMapper;
import com.qualle.truegain.model.entity.Record;
import com.qualle.truegain.model.entity.User;
import com.qualle.truegain.model.entity.Workout;
import com.qualle.truegain.service.util.DateFormatUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WorkoutMapperImpl implements WorkoutMapper {

    private final RecordMapper recordMapper;
    private final ExerciseMapper exerciseMapper;

    @Override
    public WorkoutDto toDto(Workout workout) {

        validate(workout);

        long userId = 0;

        if (workout.getUser() != null) {
            userId = workout.getUser().getId();
        }

        return WorkoutDto.builder()
                .id(workout.getId())
                .userId(userId)
                .date(DateFormatUtil.toString(workout.getDate()))
                .build();
    }

    @Override
    public Workout fromDto(WorkoutDto dto) {

        validate(dto);

        User user = User.builder().id(dto.getUserId()).build();

        Workout workout = Workout.builder()
                .id(dto.getId())
                .date(DateFormatUtil.toDate(dto.getDate()))
                .user(user)
                .build();

        List<Record> records = new ArrayList<>();

//        if (dto.getRecords() != null && !dto.getRecords().isEmpty()) {
//
//            dto.getRecords().forEach(t -> {
//                        Record record = recordMapper.fromDto(t);
//                        record.setWorkout(workout);
//                        records.add(record);
//                    }
//            );
//        }

        workout.setRecords(records);

        return workout;
    }
}
