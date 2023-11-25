package com.qualle.truegain.repository;

import com.qualle.truegain.model.entity.Exercise;
import com.qualle.truegain.model.entity.User;
import com.qualle.truegain.model.entity.Record;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Long> {

    @Query("FROM Exercise e JOIN FETCH e.records r JOIN FETCH r.workout w WHERE w.id = :id")
    List<Exercise> findAllExercisesWithRecordsByWorkoutId(long id);

    @Query("FROM Exercise e JOIN FETCH e.category c WHERE c.id = :categoryId")
    List<Exercise> findAllExercisesByCategoryId(long categoryId);

    @Query("FROM Exercise e LEFT JOIN FETCH e.records r LEFT JOIN r.workout w WHERE e.id = :id AND w.user.id = :userId AND w.date = (SELECT MAX(w.date) FROM Workout w JOIN w.records r WHERE w.user.id = :userId AND r.exercise.id = :id)")
    Exercise findByIdAndUserIdWithRecords(long id, long userId);

    @Query("SELECT w.date as date, r as record FROM Record r INNER JOIN r.workout w WHERE r.exercise.id = :id AND w.user.id = :userId")
    List<Map<String, Object>> findRecordsByUserAndExerciseGroupByDate(long id, long userId);

    @Query("SELECT COUNT(e.id) AS count, e AS exercise FROM Exercise e LEFT JOIN  e.records r LEFT JOIN r.workout w WHERE w.user.id = :userId GROUP BY e.id ORDER BY e.id")
    List<Map<String, Object>> findFrequentlyUsedExercises(long userId);

    @Query("FROM Exercise e LEFT JOIN FETCH e.image im LEFT JOIN FETCH e.icon ic WHERE e.id = :id")
    Exercise findByIdWithImage(long id);
}
