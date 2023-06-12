package com.qualle.shapeup.repository;

import com.qualle.shapeup.entity.Workout;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends CrudRepository<Workout, Long> {
}
