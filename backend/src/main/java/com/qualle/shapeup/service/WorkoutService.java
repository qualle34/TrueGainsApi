package com.qualle.shapeup.service;

import com.qualle.shapeup.api.SimpleWorkoutDto;
import com.qualle.shapeup.api.WorkoutDto;
import com.qualle.shapeup.service.basic.GenericService;

import java.util.List;

public interface WorkoutService extends GenericService<WorkoutDto, Long> {

    WorkoutDto getByUserIdAndDate(long userId, String date);

    List<SimpleWorkoutDto> getByUserId(long userId);
}
