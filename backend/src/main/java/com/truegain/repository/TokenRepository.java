package com.truegain.repository;

import com.truegain.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<User, Long> {
}
