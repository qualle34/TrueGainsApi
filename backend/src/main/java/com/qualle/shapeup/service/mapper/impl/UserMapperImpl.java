package com.qualle.shapeup.service.mapper.impl;

import com.qualle.shapeup.api.UserDto;
import com.qualle.shapeup.model.entity.User;
import com.qualle.shapeup.service.mapper.UserMapper;
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
