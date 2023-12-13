package com.qualle.truegain.service.mapper.impl;

import com.qualle.truegain.api.MeasureDto;
import com.qualle.truegain.model.entity.Dimension;
import com.qualle.truegain.service.mapper.DimensionMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DimensionMapperImpl implements DimensionMapper {

    @Override
    public MeasureDto toDto(Dimension dimension, List<String> params) {
        return MeasureDto.builder()
                .id(dimension.getId())
                .name(dimension.getName())
                .build();
    }

    @Override
    public Dimension fromDto(MeasureDto dto, List<String> params) {
        return Dimension.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
