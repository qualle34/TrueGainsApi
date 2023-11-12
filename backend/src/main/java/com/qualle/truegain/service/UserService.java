package com.qualle.truegain.service;

import com.qualle.truegain.api.*;
import com.qualle.truegain.service.basic.GenericService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends GenericService<UserDto, Long>, UserDetailsService {

    UserDto getUserWithCredentialsByLogin(String login);

    UserDto getUserWithCredentialsById(long id);

    void verifyUser(long userId, int code);

}
