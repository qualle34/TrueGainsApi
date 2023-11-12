package com.qualle.truegain.service;

import com.qualle.truegain.api.LoginPasswordAuthenticationDto;
import com.qualle.truegain.api.RefreshTokenAuthenticationDto;
import com.qualle.truegain.api.TokenDto;

public interface AuthenticationService {

    TokenDto authenticate(LoginPasswordAuthenticationDto dto);

    TokenDto token(long userId);

    TokenDto refresh(RefreshTokenAuthenticationDto dto);

    void logout(RefreshTokenAuthenticationDto dto);
}
