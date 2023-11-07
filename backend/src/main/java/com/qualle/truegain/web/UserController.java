package com.qualle.truegain.web;

import com.qualle.truegain.api.UserDto;
import com.qualle.truegain.model.security.TokenSecurityDetails;
import com.qualle.truegain.model.security.UserSecurityDetails;
import com.qualle.truegain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/private/user")
    public UserDto getUser(@AuthenticationPrincipal TokenSecurityDetails user){
        return userService.getUserByLogin(user.getUsername());
    }

    @GetMapping("/private/profile")
    public UserDto getUserProfile(@AuthenticationPrincipal TokenSecurityDetails user){
        return userService.getUserByLogin(user.getUsername());
    }

    @PutMapping("/private/user")
    public void updateUser(@AuthenticationPrincipal TokenSecurityDetails user, @RequestBody UserDto dto){

    }

    @PutMapping("/private/user/email")
    public void updateEmail(@AuthenticationPrincipal TokenSecurityDetails user, @RequestBody UserDto dto){

    }

    @PutMapping("/private/user/password")
    public void updatePassword(@AuthenticationPrincipal TokenSecurityDetails user, @RequestBody UserDto dto){

    }
}
