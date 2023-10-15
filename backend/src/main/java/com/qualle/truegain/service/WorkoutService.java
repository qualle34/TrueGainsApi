package com.qualle.truegain.service;

import com.qualle.truegain.api.SimpleWorkoutDto;
import com.qualle.truegain.api.WorkoutDto;
import com.qualle.truegain.service.basic.GenericService;

import java.util.List;

public interface WorkoutService extends GenericService<WorkoutDto, Long> {

    WorkoutDto getByUserIdAndDate(long userId, String date);

    List<SimpleWorkoutDto> getByUserId(long userId);
}
