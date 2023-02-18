package com.qualle.shapeup.service;

import com.qualle.shapeup.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User getUser(Long id);
}
