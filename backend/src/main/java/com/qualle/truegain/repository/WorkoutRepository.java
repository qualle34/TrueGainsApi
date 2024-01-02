package com.qualle.truegain.repository;

import com.qualle.truegain.model.entity.Workout;
import com.qualle.truegain.model.entity.custom.LoadDistributionByCategories;
import com.qualle.truegain.model.entity.custom.TotalLoad;
import com.qualle.truegain.model.entity.custom.WorkoutCountPerWeek;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface WorkoutRepository extends CrudRepository<Workout, Long> {

    @Query("FROM Workout w LEFT JOIN FETCH w.records r LEFT JOIN FETCH r.exercise e LEFT JOIN FETCH e.image WHERE w.id = :id")
    Workout findByIdWithRecords(long id);

    int countByUserId(long id);

    @Query(nativeQuery = true, value = "SELECT w.user_id, sum(weight * reps) AS load FROM workout w LEFT JOIN record r on w.id = r.workout_id WHERE user_id = :id group by w.user_id;")
    TotalLoad findTotalLoadByUserId(Long id);

    @Query("FROM Workout w INNER JOIN FETCH w.records r LEFT JOIN FETCH r.exercise e INNER JOIN FETCH e.image i WHERE w.user.id = :userId AND w.date BETWEEN :dateStart AND :dateEnd ")
    List<Workout> findWithImageByUserIdAndDate(long userId, LocalDateTime dateStart, LocalDateTime dateEnd);

    @Query(nativeQuery = true, value = "SELECT c.name AS name, c.id, sum(r.reps * r.weight) AS load FROM workout w LEFT JOIN record r ON w.id = r.workout_id LEFT JOIN exercise e ON r.exercise_id = e.id LEFT JOIN category c ON e.category_id = c.id WHERE w.user_id = :userId GROUP BY c.id HAVING c.id is not null")
    List<LoadDistributionByCategories> findLoadByCategoryByUserId(long userId);

    @Query(nativeQuery = true, value = "SELECT c.name AS name, c.id, sum(r.reps * r.weight) AS load FROM workout w LEFT JOIN record r ON w.id = r.workout_id LEFT JOIN exercise e ON r.exercise_id = e.id LEFT JOIN category c ON e.category_id = c.id WHERE w.user_id = :userId AND w.id = :id GROUP BY c.id HAVING c.id is not null")
    List<LoadDistributionByCategories> findLoadByCategoryByUserIdAndWorkoutId(long userId, long id);

    @Query("FROM Workout w INNER JOIN FETCH w.user u WHERE u.id = :userId ORDER BY w.date DESC")
    List<Workout> findAllByUserId(long userId);

    @Query(nativeQuery = true, value = "SELECT DATE_PART('week', workout.date) AS week, max(workout.date) AS date, COUNT(id) AS count FROM workout WHERE workout.user_id = :userId AND workout.date > CURRENT_DATE - interval '11 months' GROUP BY week ORDER BY week;")
    List<WorkoutCountPerWeek> findWorkoutCountByUserIdGroupByWeekNumber(long userId);

}
