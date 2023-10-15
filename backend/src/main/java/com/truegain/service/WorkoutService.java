package com.truegain.service;

import com.truegain.api.SimpleWorkoutDto;
import com.truegain.api.WorkoutDto;
import com.truegain.service.basic.GenericService;

import java.util.List;

public interface WorkoutService extends GenericService<WorkoutDto, Long> {

    WorkoutDto getByUserIdAndDate(long userId, String date);

    List<SimpleWorkoutDto> getByUserId(long userId);
}
