package com.truegain.api;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private long id;
    private String name;
    private String surname;
    private String birthday;
    private String gender;
}
