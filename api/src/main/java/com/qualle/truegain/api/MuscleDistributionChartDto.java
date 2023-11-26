package com.qualle.truegain.api;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MuscleDistributionChartDto {

    private Map<String, Float> thisMonthData;
    private Map<String, Float> previousMonthData;
}
