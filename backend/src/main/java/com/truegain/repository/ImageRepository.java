package com.truegain.repository;

import com.truegain.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<User, Long> {
}
