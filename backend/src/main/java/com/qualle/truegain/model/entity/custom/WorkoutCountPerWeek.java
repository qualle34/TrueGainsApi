package com.qualle.truegain.model.entity.custom;

import java.time.LocalDateTime;

public interface WorkoutCountPerWeek {

    int getWeek();

    LocalDateTime getDate();

    int getCount();
}
