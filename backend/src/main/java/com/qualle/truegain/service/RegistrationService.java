package com.qualle.truegain.service;

import com.qualle.truegain.api.ConfirmRegistrationDto;
import com.qualle.truegain.api.NewRegistrationDto;
import com.qualle.truegain.api.RefreshTokenAuthenticationDto;
import com.qualle.truegain.api.TokenDto;

public interface RegistrationService {

    RefreshTokenAuthenticationDto register(NewRegistrationDto dto);

    TokenDto confirm(ConfirmRegistrationDto dto);
}
