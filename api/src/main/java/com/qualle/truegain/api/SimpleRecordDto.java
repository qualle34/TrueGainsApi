package com.qualle.truegain.api;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleRecordDto {

    private long id;

    private float weight;

    private int reps;
}
