package com.qualle.truegain.service.impl;

import com.qualle.truegain.api.RegistrationDto;
import com.qualle.truegain.api.UserDto;
import com.qualle.truegain.service.mapper.UserMapper;
import com.qualle.truegain.model.entity.User;
import com.qualle.truegain.repository.UserRepository;
import com.qualle.truegain.service.UserService;
import com.qualle.truegain.service.mapper.RegistrationUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final RegistrationUserMapper registrationMapper;

    @Override
    public UserDto getUser(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " was not found"));

        return mapper.toDto(user);
    }

    @Override
    public void registerUser(RegistrationDto dto) {
        User user = registrationMapper.fromDto(dto);

        repository.save(user);
    }
}
