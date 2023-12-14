package com.qualle.truegain.api;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMeasureDto {

    private long measureId;
    private LocalDateTime date;
    private float value;
}
