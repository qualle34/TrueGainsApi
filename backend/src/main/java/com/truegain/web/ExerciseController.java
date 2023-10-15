package com.truegain.web;

import com.truegain.api.CategoryDto;
import com.truegain.api.ExerciseDto;
import com.truegain.service.ExerciseService;
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
