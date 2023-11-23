package com.qualle.truegain.api;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainPageDataDto {

    private Map<Integer, Integer> workoutPerWeekChartData;
    private List<SimpleWorkoutDto> recentWorkouts;
    private Map<Float, Float> muscleDistributionChartData;
    private List<SimpleExerciseDto> frequentExercises;
}
