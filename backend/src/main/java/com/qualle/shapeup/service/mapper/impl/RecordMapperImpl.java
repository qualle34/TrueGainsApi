package com.qualle.shapeup.service.mapper.impl;

import com.qualle.shapeup.api.RecordDto;
import com.qualle.shapeup.model.entity.Exercise;
import com.qualle.shapeup.model.entity.Record;
import com.qualle.shapeup.service.mapper.RecordMapper;
import org.springframework.stereotype.Component;

@Component
public class RecordMapperImpl implements RecordMapper {

    @Override
    public RecordDto toDto(Record record) {

        if (record == null) {
            throw new RuntimeException("Unable to parse entity. Entity is null");
        }

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

        if (dto == null) {
            throw new RuntimeException("Unable to parse entity. Entity is null");
        }

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
