package com.qualle.truegain.repository;

import com.qualle.truegain.model.entity.Workout;
import com.qualle.truegain.model.entity.custom.LoadDistributionByCategories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface WorkoutRepository extends CrudRepository<Workout, Long> {

    @Query("FROM Workout w INNER JOIN FETCH w.user u INNER JOIN FETCH w.records r LEFT JOIN FETCH r.exercise e LEFT JOIN FETCH e.image WHERE w.id = :id")
    Workout findByIdWithRecords(long id);

    @Query("FROM Workout w INNER JOIN FETCH w.user u INNER JOIN FETCH w.records r LEFT JOIN FETCH r.exercise e LEFT JOIN FETCH e.image WHERE u.id = :userId AND w.date BETWEEN :dateStart AND :dateEnd ")
    List<Workout> findWithImageByUserIdAndDate(long userId, LocalDateTime dateStart, LocalDateTime dateEnd);

    @Query(nativeQuery = true, value = "SELECT c.name AS name, c.id, sum(r.reps * r.weight) AS load FROM workout w LEFT JOIN record r ON w.id = r.workout_id LEFT JOIN exercise e ON r.exercise_id = e.id LEFT JOIN category c ON e.category_id = c.id WHERE w.user_id = :userId AND w.date BETWEEN :dateStart AND :dateEnd GROUP BY c.id")
    List<LoadDistributionByCategories> findWithCategoryByUserIdAndDate(long userId, LocalDateTime dateStart, LocalDateTime dateEnd);

    @Query("FROM Workout w INNER JOIN FETCH w.user u WHERE u.id = :userId ORDER BY w.date DESC")
    List<Workout> findAllByUserId(long userId);

    @Query(nativeQuery = true, value = "SELECT DATE_PART('week', workout.date) AS week, COUNT(id) FROM workout WHERE workout.user_id = :userId AND workout.date > workout.date  - interval '1 year' GROUP BY week ORDER BY week;")
    List<Map<String, Number>> findWorkoutCountByUserIdGroupByWeekNumber(long userId);
}
