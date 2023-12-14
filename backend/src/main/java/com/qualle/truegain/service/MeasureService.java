package com.qualle.truegain.service;

import com.qualle.truegain.api.MeasureDto;
import com.qualle.truegain.api.UserMeasureDto;

import java.util.List;

public interface MeasureService {

    List<MeasureDto> getMeasures();

    MeasureDto getMeasureById(long id, long userId);

    void addMeasureById(UserMeasureDto dto, long userId);
}
