package com.qualle.truegain.api.support;

import lombok.*;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto {

    private String type;

    private String message;

    private Map<String, String> additional;

    private Collection<String> stack;
}
