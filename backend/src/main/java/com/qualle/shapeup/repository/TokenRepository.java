package com.qualle.shapeup.repository;

import com.qualle.shapeup.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<User, Long> {
}
