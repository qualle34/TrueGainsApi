package com.qualle.truegain.repository;

import com.qualle.truegain.model.entity.Exercise;
import com.qualle.truegain.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Long> {

    @Query("SELECT e FROM Exercise e JOIN FETCH e.records r JOIN FETCH r.workout w WHERE w.id = :id")
    List<Exercise> findAllExercisesWithRecordsByWorkoutId(long id);

    @Query("SELECT e FROM Exercise e JOIN FETCH e.category c WHERE c.id = :categoryId")
    List<Exercise> findAllExercisesByCategoryId(long categoryId);

}
