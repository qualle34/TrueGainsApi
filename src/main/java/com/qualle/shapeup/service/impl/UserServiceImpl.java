package com.qualle.shapeup.service.impl;

import com.qualle.shapeup.entity.User;
import com.qualle.shapeup.repository.UserRepository;
import com.qualle.shapeup.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User getUser(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " was not found"));
    }
}
