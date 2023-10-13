package com.qualle.shapeup.service;

import com.qualle.shapeup.api.RegistrationDto;
import com.qualle.shapeup.api.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDto getUser(Long id);

    void registerUser(RegistrationDto dto);
}
