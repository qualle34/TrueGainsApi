package com.qualle.truegain.repository;

import com.qualle.truegain.model.entity.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface SessionRepository extends CrudRepository<Session, String> {

    @Query("FROM Session s INNER JOIN FETCH s.user u WHERE u.id = :userId")
    List<Session> findSessionsForUserId(long userId);

    @Query("FROM Session s INNER JOIN FETCH s.user WHERE s.id = :id")
    Session findByIdWithUser(String id);
}
