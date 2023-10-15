package com.qualle.truegain.service;

import com.qualle.truegain.api.AuthenticationDto;
import com.qualle.truegain.api.TokenDto;

public interface AuthenticationService {

    TokenDto authenticate(AuthenticationDto dto);

    TokenDto refresh(TokenDto dto);
}
