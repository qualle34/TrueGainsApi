package com.qualle.truegain.repository.specifications;

import com.qualle.truegain.model.entity.Workout;
import com.qualle.truegain.model.entity.Workout_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class WorkoutSpecifications {

    public static Specification<Workout> isBetweenDates(LocalDateTime start, LocalDateTime end) {
       return (root, query, builder) ->
               builder.between(root.get(Workout_.DATE), start, end);
    }

    public static Specification<Workout> hasUserId(long userId) {
        return (root, query, builder) ->
                builder.equal(root.get(Workout_.USER), userId);
    }
}
