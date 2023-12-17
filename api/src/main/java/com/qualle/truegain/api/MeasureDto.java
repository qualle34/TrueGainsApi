package com.qualle.truegain.api;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeasureDto {

    private long id;
    private String name;
    private Map<Float, Float> data;
}
