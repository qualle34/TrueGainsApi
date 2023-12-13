package com.qualle.truegain.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[a-z0-9_-]{3,16}$", message = "Validation failed for ")
    private String login;

    @NotBlank
    @Pattern(regexp = "^([a-z0-9_\\.-]+)@([a-z0-9_\\.-]+)\\.([a-z\\.]{2,6})$", message = "Invalid characters found in subscriberType")
    private String email;

    @NotBlank
    @Pattern(regexp = "^[a-z0-9_-]{6,18}$", message = "Invalid characters found in subscriberType")
    private String password;
}
