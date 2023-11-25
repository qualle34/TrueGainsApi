package com.qualle.truegain.api;

import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDto {

    private long id;
    private String name;
    private String summary;
    private String equipment;
    private String description;
    private String technique;
    private String imageLink;
    private String iconLink;
    private List<RecordDto> records;
    private Map<Float, Float> maxRepData;
    private Map<Float, Float> loadData;
}
