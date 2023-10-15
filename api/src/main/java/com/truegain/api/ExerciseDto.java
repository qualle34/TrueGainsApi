package com.truegain.api;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDto {

    private long id;
    private String name;
    private String description;
    private String imageId;
}