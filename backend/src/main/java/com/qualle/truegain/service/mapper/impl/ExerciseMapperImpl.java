package com.qualle.truegain.service.mapper.impl;

import com.qualle.truegain.api.ExerciseDto;
import com.qualle.truegain.model.entity.Exercise;
import com.qualle.truegain.service.mapper.ExerciseMapper;
import com.qualle.truegain.service.mapper.RecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExerciseMapperImpl implements ExerciseMapper {

    private final RecordMapper recordMapper;

    @Override
    public ExerciseDto toDto(Exercise exercise, List<String> params) {

        validate(exercise);

        ExerciseDto dto = ExerciseDto.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .equipment(exercise.getEquipment())
                .description(exercise.getDescription())
                .technique(exercise.getTechnique())
                .build();

        if (params.contains("image") && exercise.getImage() != null) {
            dto.setImageLink(exercise.getImage().getLink());
        }

        if (params.contains("records") && exercise.getRecords() != null) {
            dto.setRecords(recordMapper.toDto(exercise.getRecords()));
        }

        return dto;
    }

    @Override
    public Exercise fromDto(ExerciseDto dto, List<String> params) {

        validate(dto);

        return Exercise.builder().id(dto.getId()).build();
    }
}
