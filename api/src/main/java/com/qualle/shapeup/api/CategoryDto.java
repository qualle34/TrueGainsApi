package com.qualle.shapeup.api;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private long id;
    private String name;
    private List<ExerciseDto> exercises;
}
