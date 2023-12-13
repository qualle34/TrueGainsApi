package com.qualle.truegain.service.impl;

import com.qualle.truegain.api.MeasureDto;
import com.qualle.truegain.api.UserMeasureDto;
import com.qualle.truegain.repository.DimensionRepository;
import com.qualle.truegain.service.MeasureService;
import com.qualle.truegain.service.mapper.DimensionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasureServiceImpl implements MeasureService {

    private final DimensionRepository repository;
    private final DimensionMapper mapper;

    @Override
    public List<MeasureDto> getMeasures() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public UserMeasureDto getMeasureById(long id, long userId) {
        return null;
    }

    @Override
    public void addMeasureById(UserMeasureDto dto, long userId) {

    }
}