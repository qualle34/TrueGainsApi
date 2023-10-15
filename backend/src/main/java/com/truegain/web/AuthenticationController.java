package com.truegain.web;

import com.truegain.api.AuthenticationDto;
import com.truegain.api.TokenDto;
import com.truegain.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @GetMapping("/authenticate")
    public TokenDto authenticate(@RequestBody AuthenticationDto dto){
        return authenticationService.authenticate(dto);
    }

    @GetMapping("/logout")
    public void logout(){
    }
}