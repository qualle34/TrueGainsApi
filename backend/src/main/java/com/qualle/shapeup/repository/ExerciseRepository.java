package com.qualle.shapeup.repository;

import com.qualle.shapeup.model.entity.Exercise;
import com.qualle.shapeup.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Long> {

    @Query("SELECT u FROM User u JOIN FETCH u.credentials c WHERE c.login LIKE :login")
    User findUserWithCredentials(String login);



}
