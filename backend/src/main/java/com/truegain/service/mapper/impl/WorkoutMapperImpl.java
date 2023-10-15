package com.truegain.service.mapper.impl;

import com.truegain.api.RecordDto;
import com.truegain.api.WorkoutDto;
import com.truegain.model.entity.Record;
import com.truegain.model.entity.User;
import com.truegain.model.entity.Workout;
import com.truegain.service.mapper.RecordMapper;
import com.truegain.service.mapper.WorkoutMapper;
import com.truegain.service.util.DateFormatUtil;
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

        validate(workout);

        long userId = 0;

        if (workout.getUser() != null) {
            userId = workout.getUser().getId();
        }

        List<RecordDto> recordDto = new ArrayList<>();

        if (workout.getRecords() != null && !workout.getRecords().isEmpty()) {
            workout.getRecords().forEach(t -> recordDto.add(recordMapper.toDto(t)));
        }

        return WorkoutDto.builder()
                .id(workout.getId())
                .userId(userId)
                .date(DateFormatUtil.toString(workout.getDate()))
                .records(recordDto)
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
