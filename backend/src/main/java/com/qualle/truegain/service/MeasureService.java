package com.qualle.truegain.service;

import com.qualle.truegain.api.MeasureDto;
import com.qualle.truegain.api.UserMeasureDto;
import com.qualle.truegain.model.entity.User;

import java.util.List;

public interface MeasureService {

    List<MeasureDto> getMeasures();

    UserMeasureDto getMeasureById(long id, long userId);

    void addMeasureById(UserMeasureDto dto, long userId);
}
