package com.qualle.truegain.repository;

import com.qualle.truegain.model.entity.Workout;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WorkoutRepository extends CrudRepository<Workout, Long> {

    @Query("FROM Workout w INNER JOIN FETCH User u WHERE  u.id = :userId AND w.date BETWEEN :dateStart AND :dateEnd ")
    Workout findByUserIdAndDate(long userId, LocalDateTime dateStart, LocalDateTime dateEnd);

    @Query("FROM Workout w INNER JOIN FETCH User u WHERE u.id = :userId")
    List<Workout> findAllByUserId(long userId);
}
