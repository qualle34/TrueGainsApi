package com.qualle.shapeup.service.mapper.impl;

import com.qualle.shapeup.api.SimpleWorkoutDto;
import com.qualle.shapeup.model.entity.User;
import com.qualle.shapeup.model.entity.Workout;
import com.qualle.shapeup.service.mapper.SimpleWorkoutMapper;
import com.qualle.shapeup.service.util.DateFormatUtil;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SimpleWorkoutMapperImpl implements SimpleWorkoutMapper {

    @Override
    public SimpleWorkoutDto toDto(Workout workout) {

        if (workout == null) {
            throw new RuntimeException("Unable to parse entity. Entity is null");
        }

        long userId = 0;

        if (workout.getUser() != null) {
            userId = workout.getUser().getId();
        }

        int exerciseCount = workout.getRecords().stream().collect(Collectors.groupingBy(w -> w.getExercise().getId())).size();


        return SimpleWorkoutDto.builder()
                .id(workout.getId())
                .userId(userId)
                .date(DateFormatUtil.toString(workout.getDate()))
                .exerciseCount(exerciseCount)
                .build();
    }

    @Override
    public Workout fromDto(SimpleWorkoutDto dto) {

        if (dto == null) {
            throw new RuntimeException("Unable to parse entity. Entity is null");
        }

        User user = User.builder().id(dto.getUserId()).build();

        return Workout.builder()
                .id(dto.getId())
                .date(DateFormatUtil.toDate(dto.getDate()))
                .user(user)
                .build();
    }
}
