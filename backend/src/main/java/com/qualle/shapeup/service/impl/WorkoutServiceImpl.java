package com.qualle.shapeup.service.impl;

import com.qualle.shapeup.api.SimpleWorkoutDto;
import com.qualle.shapeup.api.WorkoutDto;
import com.qualle.shapeup.model.entity.Workout;
import com.qualle.shapeup.repository.WorkoutRepository;
import com.qualle.shapeup.service.WorkoutService;
import com.qualle.shapeup.service.basic.AbstractService;
import com.qualle.shapeup.service.mapper.GenericMapper;
import com.qualle.shapeup.service.mapper.SimpleWorkoutMapper;
import com.qualle.shapeup.service.mapper.WorkoutMapper;
import com.qualle.shapeup.service.util.DateFormatUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutServiceImpl extends AbstractService<Workout, WorkoutDto, Long> implements WorkoutService {

    private final WorkoutRepository repository;
    private final WorkoutMapper workoutMapper;
    private final SimpleWorkoutMapper simpleWorkoutMapper;

    @Override
    protected CrudRepository<Workout, Long> getRepository() {
        return repository;
    }

    @Override
    protected GenericMapper<Workout, WorkoutDto> getMapper() {
        return workoutMapper;
    }

    @Override
    public WorkoutDto getByUserIdAndDate(long userId, String date) {
        LocalDate localDate = DateFormatUtil.toDate(date).toLocalDate();
        LocalDateTime dateStart = localDate.atStartOfDay();
        LocalDateTime dateEnd = LocalDateTime.of(localDate, LocalTime.MAX);


        Workout workout = repository.findByUserIdAndDate(userId, dateStart, dateEnd);

        WorkoutDto dto = workoutMapper.toDto(workout);

        return dto;
    }

    @Override
    public List<SimpleWorkoutDto> getByUserId(long userId) {

        List<Workout> workouts = repository.findAllByUserId(userId);

        List<SimpleWorkoutDto> dtos = simpleWorkoutMapper.toDto(workouts);

        return dtos;
    }
}
