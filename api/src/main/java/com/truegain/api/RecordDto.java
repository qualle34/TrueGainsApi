package com.truegain.api;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordDto {

    private long id;

    private long exerciseId;

    private float weight;

    private int reps;
}
