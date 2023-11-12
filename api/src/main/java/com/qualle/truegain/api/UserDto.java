package com.qualle.truegain.api;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private long id;
    private String name;
    private String birthday;
    private String gender;
    private String imageLink;
    private CredentialsDto credentials;
}
