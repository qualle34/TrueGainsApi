package com.qualle.truegain.repository;

import com.qualle.truegain.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<User, Long> {
}
