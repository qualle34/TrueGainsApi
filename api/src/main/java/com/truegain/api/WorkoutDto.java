package com.truegain.api;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutDto {

    private long id;
    @NotBlank
    private String date;
    private long userId;
    private List<RecordDto> records;
}
