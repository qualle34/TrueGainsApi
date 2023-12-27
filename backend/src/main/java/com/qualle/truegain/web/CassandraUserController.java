package com.qualle.truegain.web;

import com.qualle.truegain.api.ProfileDto;
import com.qualle.truegain.api.UserDto;
import com.qualle.truegain.model.cassandra.CassandraUserRepository;
import com.qualle.truegain.model.cassandra.User;
import com.qualle.truegain.model.security.TokenSecurityDetails;
import com.qualle.truegain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CassandraUserController {

    private final CassandraUserRepository repository;

    @GetMapping("/cassandra/user")
    public Iterable<User> getUserProfile(@AuthenticationPrincipal TokenSecurityDetails user){
        return repository.findAll();
    }

}
