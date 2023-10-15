package com.qualle.truegain.service;

import com.qualle.truegain.api.RegistrationDto;
import com.qualle.truegain.api.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDto getUser(Long id);

    void registerUser(RegistrationDto dto);
}
