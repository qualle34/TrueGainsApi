package com.truegain.repository;

import com.truegain.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN FETCH u.credentials c WHERE c.login LIKE :login")
    User findUserWithCredentials(String login);
}
