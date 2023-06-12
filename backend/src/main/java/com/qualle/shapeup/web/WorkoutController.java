package com.qualle.shapeup.web;

import com.qualle.shapeup.api.CategoryDto;
import com.qualle.shapeup.api.ExerciseDto;
import com.qualle.shapeup.api.UserDto;
import com.qualle.shapeup.api.WorkoutDto;
import com.qualle.shapeup.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    @GetMapping("/workout/{id}")
    public WorkoutDto getWorkout(@PathVariable(required = false) Long id){
        return null;
    }
}
