package com.qualle.shapeup.api;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordDto {

    private long id;

    private int value;

    private Unit unit;

    private ExerciseDto exercise;

    private WorkoutDto workout;
}
