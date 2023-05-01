package com.qualle.shapeup.service;

import com.qualle.shapeup.api.AuthenticationDto;
import com.qualle.shapeup.api.TokenDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationService {

    TokenDto authenticate(AuthenticationDto dto);

    TokenDto refresh(TokenDto dto);
}
