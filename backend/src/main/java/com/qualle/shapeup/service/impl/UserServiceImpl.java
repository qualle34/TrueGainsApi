package com.qualle.shapeup.service.impl;

import com.qualle.shapeup.api.RegistrationDto;
import com.qualle.shapeup.api.UserDto;
import com.qualle.shapeup.entity.User;
import com.qualle.shapeup.repository.UserRepository;
import com.qualle.shapeup.service.UserService;
import com.qualle.shapeup.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public UserDto getUser(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " was not found"));

        return mapper.toDto(user);
    }

    @Override
    public void registerUser(RegistrationDto dto) {
        User user = mapper.fromDto(dto);

        repository.save(user);
    }
}
