package com.qualle.truegain.api;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleExerciseDto {

    private long id;
    private String name;
    private String equipment;
    private int recordCount;
}
