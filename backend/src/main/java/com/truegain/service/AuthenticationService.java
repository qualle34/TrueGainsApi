package com.truegain.service;

import com.truegain.api.AuthenticationDto;
import com.truegain.api.TokenDto;

public interface AuthenticationService {

    TokenDto authenticate(AuthenticationDto dto);

    TokenDto refresh(TokenDto dto);
}
