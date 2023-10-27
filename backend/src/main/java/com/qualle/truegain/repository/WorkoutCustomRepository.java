package com.qualle.truegain.repository;

import com.qualle.truegain.model.entity.Workout;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkoutCustomRepository {

    List<Workout> findRecentByUserIdWithLimit(long userId, int amount);
}
