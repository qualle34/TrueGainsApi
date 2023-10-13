package com.qualle.shapeup.web;

import com.qualle.shapeup.api.RegistrationDto;
import com.qualle.shapeup.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping("/register")
    public void registerUser(@RequestBody RegistrationDto dto) {
        userService.registerUser(dto);
    }
}
