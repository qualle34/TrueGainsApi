package com.qualle.truegain.api;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenAuthenticationDto {

    private String token;
}
