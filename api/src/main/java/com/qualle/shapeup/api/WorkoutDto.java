package com.qualle.shapeup.api;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutDto {

    private Long id;
    private String date;
    private Long userId;
    private List<RecordDto> records;
}
