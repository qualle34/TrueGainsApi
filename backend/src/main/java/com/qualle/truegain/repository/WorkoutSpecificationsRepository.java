package com.qualle.truegain.repository;

import com.qualle.truegain.model.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WorkoutSpecificationsRepository extends JpaRepository<Workout, Long>, JpaSpecificationExecutor<Workout> {

}
