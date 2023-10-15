package com.qualle.truegain.service.mapper.impl;

import com.qualle.truegain.api.RegistrationDto;
import com.qualle.truegain.model.entity.Credentials;
import com.qualle.truegain.model.entity.User;
import com.qualle.truegain.service.mapper.RegistrationUserMapper;
import org.springframework.stereotype.Component;

@Component
public class RegistrationUserMapperImpl implements RegistrationUserMapper {

    @Override
    public RegistrationDto toDto(User user) {
        return null; // Used only for user creation
    }

    @Override
    public User fromDto(RegistrationDto dto) {

        validate(dto);

        User user = User.builder()
                .name(dto.getName())
                .birthday(dto.getBirthday())
                .gender(dto.getGender())
                .build();

        Credentials credentials = Credentials.builder()
                .user(user)
                .login(dto.getLogin())
                .password(dto.getPassword())
                .build();

        user.setCredentials(credentials);

        return user;
    }
}
