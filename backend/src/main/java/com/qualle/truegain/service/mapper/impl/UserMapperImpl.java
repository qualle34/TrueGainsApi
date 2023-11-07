package com.qualle.truegain.service.mapper.impl;

import com.qualle.truegain.api.CredentialsDto;
import com.qualle.truegain.api.UserDto;
import com.qualle.truegain.model.entity.User;
import com.qualle.truegain.service.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user, List<String> params) {

        validate(user);

        UserDto dto = UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .birthday(user.getBirthday())
                .gender(user.getGender())
                .build();

        if (params.contains("credentials") && user.getCredentials() != null) {
            CredentialsDto credentialsDto = CredentialsDto.builder()
                    .login(user.getCredentials().getLogin())
                    .password(user.getCredentials().getPassword())
                    .build();

            dto.setCredentials(credentialsDto);
        }

        return dto;
    }

    @Override
    public User fromDto(UserDto dto, List<String> params) {

        validate(dto);

        return User.builder()
                .name(dto.getName())
                .birthday(dto.getBirthday())
                .gender(dto.getGender())
                .build();
    }
}
