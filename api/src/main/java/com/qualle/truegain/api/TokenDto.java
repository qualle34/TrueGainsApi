package com.qualle.truegain.api;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {

    private String accessToken;
    private String accessTokenExpiredAt;
    private String refreshToken;
    private String refreshTokenExpiredAt;
}
