package com.qualle.truegain.api;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleWorkoutDto {

    private long id;
    private String date;
    private long userId;
    private int exerciseCount;
}
