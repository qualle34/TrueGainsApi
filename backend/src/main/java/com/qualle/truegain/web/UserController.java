package com.qualle.truegain.web;

import com.qualle.truegain.api.UserDto;
import com.qualle.truegain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public UserDto getUser(@PathVariable(required = false) Long id){
        return userService.getUser(id);
    }

    @GetMapping("/profile")
    public UserDto getUserProfile(){
        return userService.getUser(1L); // todo from token
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserDto dto){

    }

    @PutMapping("/user/email")
    public void updateEmail(@RequestBody UserDto dto){

    }

    @PutMapping("/user/password")
    public void updatePassword(@RequestBody UserDto dto){

    }
}
