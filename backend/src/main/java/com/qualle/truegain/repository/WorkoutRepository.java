package com.qualle.truegain.repository;

import com.qualle.truegain.model.entity.Workout;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface WorkoutRepository extends CrudRepository<Workout, Long> {

    @Query("FROM Workout w INNER JOIN FETCH User u WHERE  u.id = :userId AND w.date BETWEEN :dateStart AND :dateEnd ")
    Workout findByUserIdAndDate(long userId, LocalDateTime dateStart, LocalDateTime dateEnd);

    @Query("FROM Workout w INNER JOIN FETCH User u WHERE u.id = :userId ORDER BY w.date DESC")
    List<Workout> findAllByUserId(long userId);

    @Query(nativeQuery = true, value = "SELECT DATE_PART('week', workout.date) AS week, COUNT(id) FROM workout WHERE workout.user_id = :userId AND workout.date > workout.date  - interval '1 year' GROUP BY week ORDER BY week;")
    List<Map<String, Number>> findWorkoutCountByUserIdGroupByWeekNumber(long userId);
}
