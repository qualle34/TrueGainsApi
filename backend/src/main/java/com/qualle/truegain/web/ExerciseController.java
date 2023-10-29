package com.qualle.truegain.web;

import com.qualle.truegain.api.CategoryDto;
import com.qualle.truegain.api.ExerciseDto;
import com.qualle.truegain.service.ExerciseService;
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
    public List<CategoryDto> getCategories(@RequestParam(name = "fetch", required = false) String fetch) {

        if ("exercise".equals(fetch)) {
            return exerciseService.getCategoriesWithExercises();
        }

        return exerciseService.getCategories();
    }

    @GetMapping("/exercise")
    public List<ExerciseDto> getCategories(@RequestParam(name = "category-id") long categoryId) {
        return exerciseService.getExercisesByCategory(categoryId);
    }

    @GetMapping("/exercise/{id}")
    public ExerciseDto getExercise(@PathVariable long id, @RequestParam(name = "user-id", required = false, defaultValue = "0") long userId) {

        if (userId != 0) {
            return exerciseService.getExerciseWithRecordsByIdForUserId(id, userId);
        }

        return exerciseService.getExercise(id);
    }
}
