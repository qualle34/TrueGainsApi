package com.qualle.shapeup.repository;

import com.qualle.shapeup.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<User, Long> {
}
