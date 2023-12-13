package com.qualle.truegain.service.impl;

import com.qualle.truegain.api.MainPageDataDto;
import com.qualle.truegain.api.UserDto;
import com.qualle.truegain.api.WorkoutDto;
import com.qualle.truegain.model.entity.Workout;
import com.qualle.truegain.service.ExerciseService;
import com.qualle.truegain.service.MainService;
import com.qualle.truegain.service.UserService;
import com.qualle.truegain.service.WorkoutService;
import com.qualle.truegain.service.basic.AbstractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;

    @Override
    public MainPageDataDto getMainPageData(long userId) {

        return MainPageDataDto.builder()
                .workoutPerWeekChartData(workoutService.getCountByUserIdGroupByWeekNumber(userId))
                .recentWorkouts(workoutService.getRecentByUserIdWithLimit(userId, 2))
                .frequentExercises(exerciseService.getFrequentlyUsedExercises(userId, 3))
                .muscleDistributionChartData(workoutService.getMuscleDistributionChartData(userId))
                .build();
    }
}
