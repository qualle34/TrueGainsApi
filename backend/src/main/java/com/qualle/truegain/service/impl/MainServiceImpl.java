package com.qualle.truegain.service.impl;

import com.qualle.truegain.api.MainPageDataDto;
import com.qualle.truegain.service.MainService;
import com.qualle.truegain.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    private final WorkoutService workoutService;

    @Override
    public MainPageDataDto getMainPageData(long userId) {

        return MainPageDataDto.builder()
                .workoutPerWeekChartData(workoutService.getCountByUserIdGroupByWeekNumber(userId))
                .recentWorkouts(workoutService.getRecentByUserIdWithLimit(userId, 2))
                .muscleDistributionChartData(null)
                .build();
    }
}
