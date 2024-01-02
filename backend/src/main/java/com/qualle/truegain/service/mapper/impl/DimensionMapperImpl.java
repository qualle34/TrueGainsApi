package com.qualle.truegain.service.mapper.impl;

import com.qualle.truegain.api.MeasureDto;
import com.qualle.truegain.model.entity.Dimension;
import com.qualle.truegain.model.entity.UserDimension;
import com.qualle.truegain.service.mapper.DimensionMapper;
import com.qualle.truegain.service.util.DateFormatUtil;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DimensionMapperImpl implements DimensionMapper {

    @Override
    public MeasureDto toDto(Dimension dimension, List<String> params) {

        Map<Float, Float> data = new HashMap<>();

        if (params.contains("user_measures") &&  dimension.getUserDimensions() != null) {

            dimension.getUserDimensions().forEach( d -> {
                        float dayNum = DateFormatUtil.getDayNumber(d.getDate());
                        data.put(dayNum, d.getValue());
                    }
            );
        }


        return MeasureDto.builder()
                .id(dimension.getId())
                .name(dimension.getName())
                .data(data)
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
