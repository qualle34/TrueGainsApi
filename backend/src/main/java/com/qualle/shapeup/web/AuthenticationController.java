package com.qualle.shapeup.web;

import com.qualle.shapeup.api.AuthenticationDto;
import com.qualle.shapeup.api.TokenDto;
import com.qualle.shapeup.service.AuthenticationService;
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
