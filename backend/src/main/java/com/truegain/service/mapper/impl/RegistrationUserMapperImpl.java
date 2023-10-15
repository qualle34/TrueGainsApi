package com.truegain.service.mapper.impl;

import com.truegain.api.RegistrationDto;
import com.truegain.model.entity.User;
import com.truegain.service.mapper.RegistrationUserMapper;
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
