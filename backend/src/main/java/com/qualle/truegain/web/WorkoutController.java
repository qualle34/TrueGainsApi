package com.qualle.truegain.web;

import com.qualle.truegain.api.MainPageDataDto;
import com.qualle.truegain.api.SimpleWorkoutDto;
import com.qualle.truegain.api.WorkoutDto;
import com.qualle.truegain.model.exception.BadRequestException;
import com.qualle.truegain.model.security.TokenSecurityDetails;
import com.qualle.truegain.model.security.UserSecurityDetails;
import com.qualle.truegain.service.MainService;
import com.qualle.truegain.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    private final MainService mainService;

    @GetMapping("/private/main")
    public MainPageDataDto getWorkout(@AuthenticationPrincipal TokenSecurityDetails user) {
        return mainService.getMainPageData(user.getId());
    }

    @GetMapping("/private/workout/{id}")
    public WorkoutDto getWorkout(@AuthenticationPrincipal TokenSecurityDetails user, @PathVariable Long id) {

        if (id == 0) {
            throw new BadRequestException();
        }

        return workoutService.getByIdAndUser(id, user.getId());
    }

    @GetMapping("/private/workout/simple")
    public List<SimpleWorkoutDto> getWorkoutByUserId(@AuthenticationPrincipal TokenSecurityDetails user) {
        return workoutService.getByUserId(user.getId());
    }

    @GetMapping("/private/workout")
    public WorkoutDto getWorkoutByUserIdAndDate(@AuthenticationPrincipal TokenSecurityDetails user, @RequestParam String date) {

        if (date == null || date.isEmpty()) {
            throw new BadRequestException();
        }

        return workoutService.getByUserIdAndDate(user.getId(), date);
    }

    @PutMapping("/private/workout/{id}")
    public void saveWorkout(@AuthenticationPrincipal TokenSecurityDetails user, @PathVariable Long id, @RequestBody @Validated WorkoutDto dto) {

        if (dto == null) {
            throw new BadRequestException();
        }

        if (dto.getId() == 0) {
            dto.setId(id);
        }

        workoutService.updateWorkoutForUser(dto, user.getId());
    }

    @DeleteMapping("/private/workout/{id}")
    public void deleteWorkout(@AuthenticationPrincipal TokenSecurityDetails user, @PathVariable Long id) {

        if (id == 0) {
            throw new BadRequestException();
        }

        workoutService.deleteWorkoutForUser(id, user.getId());
    }
}
