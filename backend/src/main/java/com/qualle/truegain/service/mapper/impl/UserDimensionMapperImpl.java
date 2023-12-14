package com.qualle.truegain.service.mapper.impl;

import com.qualle.truegain.api.UserMeasureDto;
import com.qualle.truegain.model.entity.Dimension;
import com.qualle.truegain.service.mapper.UserDimensionMapper;

import java.util.List;

public class UserDimensionMapperImpl implements UserDimensionMapper {

    @Override
    public UserMeasureDto toDto(Dimension dimension, List<String> params) {


        return UserMeasureDto.builder()
                .build();
    }

    @Override
    public Dimension fromDto(UserMeasureDto userMeasureDto, List<String> params) {
        return null;
    }
}
