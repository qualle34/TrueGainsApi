package com.qualle.truegain.web;

import com.qualle.truegain.api.ConfirmRegistrationDto;
import com.qualle.truegain.api.NewRegistrationDto;
import com.qualle.truegain.api.RefreshTokenAuthenticationDto;
import com.qualle.truegain.api.TokenDto;
import com.qualle.truegain.repository.ConfirmationRepository;
import com.qualle.truegain.repository.UserRepository;
import com.qualle.truegain.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/registration/new")
    public RefreshTokenAuthenticationDto registerNewUser(@RequestBody NewRegistrationDto dto) {
        return registrationService.register(dto);
    }

    @PostMapping("/registration/confirm")
    public TokenDto confirmRegistration(@RequestBody ConfirmRegistrationDto dto) {
        return registrationService.confirm(dto);
    }
}
