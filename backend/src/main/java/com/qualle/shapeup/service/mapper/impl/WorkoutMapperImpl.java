package com.qualle.shapeup.service.mapper.impl;

import com.qualle.shapeup.api.RecordDto;
import com.qualle.shapeup.api.WorkoutDto;
import com.qualle.shapeup.model.entity.Record;
import com.qualle.shapeup.model.entity.User;
import com.qualle.shapeup.model.entity.Workout;
import com.qualle.shapeup.service.mapper.RecordMapper;
import com.qualle.shapeup.service.mapper.WorkoutMapper;
import com.qualle.shapeup.service.util.DateFormatUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WorkoutMapperImpl implements WorkoutMapper {

    private final RecordMapper recordMapper;


    @Override
    public WorkoutDto toDto(Workout workout) {

        if (workout == null) {
            throw new RuntimeException("Unable to parse entity. Entity is null");
        }

        long userId = 0;

        if (workout.getUser() != null) {
            userId = workout.getUser().getId();
        }

        List<RecordDto> recordDto = new ArrayList<>();

        if (workout.getRecords() != null && !workout.getRecords().isEmpty()) {
            workout.getRecords().forEach(t -> recordDto.add(recordMapper.toDto(t)));
        }


        WorkoutDto dto = WorkoutDto.builder()
                .id(workout.getId())
                .userId(userId)
                .date(DateFormatUtil.toString(workout.getDate()))
                .records(recordDto)
                .build();


        return dto;
    }

    @Override
    public Workout fromDto(WorkoutDto dto) {

        if (dto == null) {
            throw new RuntimeException("Unable to parse entity. Entity is null");
        }

        User user = User.builder().id(dto.getUserId()).build();

        Workout workout = Workout.builder()
                .id(dto.getId())
                .date(DateFormatUtil.toDate(dto.getDate()))
                .user(user)
                .build();

        List<Record> records = new ArrayList<>();

        if (dto.getRecords() != null && !dto.getRecords().isEmpty()) {

            dto.getRecords().forEach(t -> {
                        Record record = recordMapper.fromDto(t);
                        record.setWorkout(workout);
                        records.add(record);
                    }
            );
        }

        workout.setRecords(records);

        return workout;
    }
}
