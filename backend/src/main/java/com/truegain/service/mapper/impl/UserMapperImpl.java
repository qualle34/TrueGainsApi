package com.truegain.service.mapper.impl;

import com.truegain.api.UserDto;
import com.truegain.model.entity.User;
import com.truegain.service.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto toDto(User user) {
        return null;
    }

    @Override
    public User fromDto(UserDto userDto) {
        return null;
    }
}
