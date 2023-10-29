package com.qualle.truegain.repository;

import com.qualle.truegain.model.entity.Exercise;
import com.qualle.truegain.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Long> {

    @Query("FROM Exercise e JOIN FETCH e.records r JOIN FETCH r.workout w WHERE w.id = :id")
    List<Exercise> findAllExercisesWithRecordsByWorkoutId(long id);

    @Query("FROM Exercise e JOIN FETCH e.category c WHERE c.id = :categoryId")
    List<Exercise> findAllExercisesByCategoryId(long categoryId);

    @Query("FROM Exercise e LEFT JOIN FETCH e.records r LEFT JOIN r.workout w WHERE e.id = :id AND w.user.id = :userId AND w.date = (SELECT MAX(w.date) FROM Workout w JOIN w.records r WHERE w.user.id = :userId AND r.exercise.id = :id)")
    Exercise findByIdAndUserIdWithRecords(long id, long userId);
}
