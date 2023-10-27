package com.qualle.truegain.service.mapper.impl;

import com.qualle.truegain.api.ExerciseDto;
import com.qualle.truegain.api.RecordDto;
import com.qualle.truegain.model.entity.Exercise;
import com.qualle.truegain.service.mapper.ExerciseMapper;
import com.qualle.truegain.service.mapper.RecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ExerciseMapperImpl implements ExerciseMapper {

    private final RecordMapper recordMapper;

    @Override
    public ExerciseDto toDto(Exercise exercise) {
        return ExerciseDto.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .equipment(exercise.getEquipment())
//                .imageId(exercise.getImage().getLink())
                .description(exercise.getDescription())
//                .records(recordMapper.toDto(exercise.getRecords()))
                .build();
    }

    @Override
    public Exercise fromDto(ExerciseDto exerciseDto) {
        return null;
    }
}
