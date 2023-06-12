package com.qualle.shapeup.web;

import com.qualle.shapeup.api.CategoryDto;
import com.qualle.shapeup.api.ExerciseDto;
import com.qualle.shapeup.service.ExerciseService;
import com.qualle.shapeup.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping("/category")
    public List<CategoryDto> getCategories(){
        return exerciseService.getCategories();
    }

    @GetMapping("/exercise")
    public List<ExerciseDto> getCategories(@RequestParam(name = "category-id") long categoryId){
        return exerciseService.getExercisesByCategory(categoryId);
    }

    @GetMapping("/exercise/{id}")
    public List<ExerciseDto> getExercise(@PathVariable(required = false) long id) {
        return exerciseService.getExercise(id);
    }
}
