package com.qualle.truegain.service.mapper.impl;

import com.qualle.truegain.api.RecordDto;
import com.qualle.truegain.service.mapper.RecordMapper;
import com.qualle.truegain.model.entity.Exercise;
import com.qualle.truegain.model.entity.Record;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecordMapperImpl implements RecordMapper {

    @Override
    public RecordDto toDto(Record record, List<String> params) {

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
    public Record fromDto(RecordDto dto, List<String> params) {

        validate(dto);

        Record record = Record.builder()
                .id(dto.getId())
                .reps(dto.getReps())
                .weight(dto.getWeight())
                .build();

        if (params.contains("exercise")) {
            record.setExercise(Exercise.builder()
                    .id(dto.getExerciseId())
                    .build());
        }

        return record;
    }
}
