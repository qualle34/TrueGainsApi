package com.qualle.truegain.api;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDto {

    private long id;
    private String name;
    private String equipment;
    private String description;
    private String imageLink;
    private List<RecordDto> records;
}
