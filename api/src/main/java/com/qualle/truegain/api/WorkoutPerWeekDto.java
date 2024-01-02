package com.qualle.truegain.api;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutPerWeekDto {

    private Integer day;
    private Integer week;
    private Integer count;
}
