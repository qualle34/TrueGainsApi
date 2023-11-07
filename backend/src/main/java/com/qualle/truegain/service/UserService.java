package com.qualle.truegain.service;

import com.qualle.truegain.api.RegistrationDto;
import com.qualle.truegain.api.UserDto;
import com.qualle.truegain.model.entity.User;
import com.qualle.truegain.service.basic.GenericService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends GenericService<UserDto, Long>, UserDetailsService {

    UserDto getUserByLogin(String login);

    void registerUser(RegistrationDto dto);
}
