package com.qualle.shapeup.service.impl;

import com.qualle.shapeup.api.AuthenticationDto;
import com.qualle.shapeup.api.TokenDto;
import com.qualle.shapeup.model.entity.User;
import com.qualle.shapeup.repository.UserRepository;
import com.qualle.shapeup.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    @Override
    public TokenDto authenticate(AuthenticationDto dto) {

        User user = userRepository.findUserWithCredentials(dto.getLogin());


        return new TokenDto().builder()
                .accessToken(String.valueOf(user.getId()))
                .build();
    }

    @Override
    public TokenDto refresh(TokenDto dto) {
        return null;
    }
}
