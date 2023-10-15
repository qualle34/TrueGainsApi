package com.qualle.truegain.service.mapper.impl;

import com.qualle.truegain.api.UserDto;
import com.qualle.truegain.service.mapper.UserMapper;
import com.qualle.truegain.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {

        validate(user);

        return UserDto.builder()
                .name(user.getName())
                .birthday(user.getBirthday())
                .gender(user.getGender())
                .build();
    }

    @Override
    public User fromDto(UserDto dto) {

        validate(dto);

        return User.builder()
                .name(dto.getName())
                .birthday(dto.getBirthday())
                .gender(dto.getGender())
                .build();
    }
}
