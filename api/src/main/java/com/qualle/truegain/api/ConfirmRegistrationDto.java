package com.qualle.truegain.api;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmRegistrationDto {

    private int code;
    private String token;
}
