package com.qualle.shapeup.service.mapper.impl;

import com.qualle.shapeup.api.RegistrationDto;
import com.qualle.shapeup.model.entity.User;
import com.qualle.shapeup.service.mapper.RegistrationUserMapper;
import org.springframework.stereotype.Component;

@Component
public class RegistrationUserMapperImpl implements RegistrationUserMapper {
    @Override
    public RegistrationDto toDto(User user) {
        return null;
    }

    @Override
    public User fromDto(RegistrationDto registrationDto) {
        return null;
    }
}
