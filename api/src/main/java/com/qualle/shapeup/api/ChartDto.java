package com.qualle.shapeup.api;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChartDto {

    private String name;
    private String description;
    private String measureType;
    private String imageLink;
    private Map<String, Float> data;
}
