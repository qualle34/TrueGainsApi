package com.truegain.service.impl;

import com.truegain.api.RegistrationDto;
import com.truegain.api.UserDto;
import com.truegain.model.entity.User;
import com.truegain.repository.UserRepository;
import com.truegain.service.UserService;
import com.truegain.service.mapper.RegistrationUserMapper;
import com.truegain.service.mapper.UserMapper;
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
