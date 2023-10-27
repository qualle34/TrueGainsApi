package com.qualle.truegain.api;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutDto {

    private long id;
    @NotBlank
    private String date;
    private long userId;
    private List<ExerciseDto> exercises;
    private List<WorkoutVolumeDto> volumeForExercises;
    private List<WorkoutVolumeDto> volumeForBodyParts;
}
