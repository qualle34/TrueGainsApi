package com.qualle.truegain.web;

import com.qualle.truegain.api.CategoryDto;
import com.qualle.truegain.api.ExerciseDto;
import com.qualle.truegain.model.security.TokenSecurityDetails;
import com.qualle.truegain.model.security.UserSecurityDetails;
import com.qualle.truegain.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ExerciseDto getExercise(@PathVariable long id) {
        return exerciseService.getExercise(id);
    }


    @GetMapping("/private/exercise/{id}")
    public ExerciseDto getExercise(@AuthenticationPrincipal TokenSecurityDetails user, @PathVariable long id) {
        return exerciseService.getExerciseWithRecordsByIdForUserId(id, user.getId());
    }
}
