package com.qualle.truegain.web;

import com.qualle.truegain.api.MainPageDataDto;
import com.qualle.truegain.api.WorkoutDto;
import com.qualle.truegain.model.exception.BadRequestException;
import com.qualle.truegain.service.MainService;
import com.qualle.truegain.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/main")
    public MainPageDataDto getWorkout(@RequestParam Long userId) {

        if (userId == 0) {
            throw new BadRequestException();
        }

        return mainService.getMainPageData(userId);
    }

}
