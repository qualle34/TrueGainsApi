package com.truegain.service.impl;

import com.truegain.api.AuthenticationDto;
import com.truegain.api.TokenDto;
import com.truegain.model.entity.User;
import com.truegain.repository.UserRepository;
import com.truegain.service.AuthenticationService;
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
