package com.qualle.truegain.api;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewRegistrationDto {

    @NotBlank
    private String name;
    private String birthday;
    private String gender;

    @NotBlank
    private String login;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
