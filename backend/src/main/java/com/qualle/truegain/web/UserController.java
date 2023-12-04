package com.qualle.truegain.web;

import com.qualle.truegain.api.UserDto;
import com.qualle.truegain.api.support.ErrorType;
import com.qualle.truegain.model.exception.BadRequestException;
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
        return userService.getUserWithCredentialsById(user.getId());
    }

    @PutMapping("/private/user/{id}")
    public void updateUser(@AuthenticationPrincipal TokenSecurityDetails user, @RequestBody UserDto dto, @PathVariable Long id){

        if (user.getId() != id) {
            throw new BadRequestException("Unable to verify user authenticity", ErrorType.BAD_REQUEST);
        }

        userService.save(dto, user.getId());
    }

    @PutMapping("/private/user/{id}/email")
    public void updateEmail(@AuthenticationPrincipal TokenSecurityDetails user, @RequestBody UserDto dto, @PathVariable Long id){
        if (user.getId() != id) {
            throw new BadRequestException("Unable to verify user authenticity", ErrorType.BAD_REQUEST);
        }
    }

    @PutMapping("/private/user/{id}/password")
    public void updatePassword(@AuthenticationPrincipal TokenSecurityDetails user, @RequestBody UserDto dto, @PathVariable Long id) {
        if (user.getId() != id) {
            throw new BadRequestException("Unable to verify user authenticity", ErrorType.BAD_REQUEST);
        }
    }
}
