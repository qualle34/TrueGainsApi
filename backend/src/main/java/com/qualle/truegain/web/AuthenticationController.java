package com.qualle.truegain.web;

import com.qualle.truegain.api.LoginPasswordAuthenticationDto;
import com.qualle.truegain.api.RefreshTokenAuthenticationDto;
import com.qualle.truegain.api.TokenDto;
import com.qualle.truegain.service.AuthenticationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginPasswordAuthenticationDto dto) {
        return authenticationService.authenticate(dto);
    }

    @PostMapping("/refresh")
    public TokenDto refresh(@RequestBody RefreshTokenAuthenticationDto dto) {
        return authenticationService.refresh(dto);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody RefreshTokenAuthenticationDto dto) {
        authenticationService.logout(dto);
    }
}
