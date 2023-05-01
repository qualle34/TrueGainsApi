package com.qualle.shapeup.api;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {

    private String name;
    private String surname;
    private String birthday;
    private String gender;
    private String login;
    private String password;
}
