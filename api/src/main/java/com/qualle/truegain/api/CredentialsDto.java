package com.qualle.truegain.api;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CredentialsDto {

    private String login;
    private String password;
}
