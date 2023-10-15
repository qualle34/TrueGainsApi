package com.truegain.service;

import com.truegain.api.RegistrationDto;
import com.truegain.api.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDto getUser(Long id);

    void registerUser(RegistrationDto dto);
}
