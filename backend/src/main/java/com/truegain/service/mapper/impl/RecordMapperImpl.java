package com.truegain.service.mapper.impl;

import com.truegain.api.RecordDto;
import com.truegain.model.entity.Exercise;
import com.truegain.model.entity.Record;
import com.truegain.service.mapper.RecordMapper;
import org.springframework.stereotype.Component;

@Component
public class RecordMapperImpl implements RecordMapper {

    @Override
    public RecordDto toDto(Record record) {

        validate(record);

        long exerciseId = 0;

        if (record.getExercise() != null) {
            exerciseId = record.getExercise().getId();
        }

        return RecordDto.builder()
                .id(record.getId())
                .exerciseId(exerciseId)
                .weight(record.getWeight())
                .reps(record.getReps())
                .build();
    }

    @Override
    public Record fromDto(RecordDto dto) {

        validate(dto);

        Exercise exercise = Exercise.builder()
                .id(dto.getExerciseId())
                .build();

        return Record.builder()
                .id(dto.getId())
                .exercise(exercise)
                .reps(dto.getReps())
                .weight(dto.getWeight())
                .build();
    }
}
