package com.qualle.truegain.repository;

import com.qualle.truegain.model.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("FROM User u LEFT JOIN FETCH u.credentials c WHERE c.login LIKE :login")
    User findUserWithCredentials(String login);

    @Query("FROM User u LEFT JOIN FETCH u.credentials c WHERE u.id = :id")
    User findUserWithCredentials(long id);

    @Query("FROM User u LEFT JOIN FETCH u.confirmation c WHERE u.id = :id")
    User findUserWithConfirmation(long id);

    @Query("FROM User u LEFT JOIN FETCH u.credentials c WHERE c.login LIKE :login OR c.email LIKE :email")
    List<User> findUserWithCredentialsByLoginOrEmail(String login, String email);

    @Modifying
    @Query("DELETE FROM Confirmation c WHERE c.userId = :userId")
    void deleteConfirmationForUser(long userId);

    @Modifying
    @Query("DELETE FROM Confirmation c WHERE :oldDate > c.createdAt")
    void deleteConfirmationAfterDate(LocalDateTime oldDate);
}
