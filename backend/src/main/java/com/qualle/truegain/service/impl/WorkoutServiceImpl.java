package com.qualle.truegain.service.impl;

import com.qualle.truegain.api.ExerciseDto;
import com.qualle.truegain.api.SimpleWorkoutDto;
import com.qualle.truegain.api.WorkoutDto;
import com.qualle.truegain.api.WorkoutVolumeDto;
import com.qualle.truegain.model.entity.User;
import com.qualle.truegain.model.entity.Workout;
import com.qualle.truegain.model.exception.EntityNotFoundException;
import com.qualle.truegain.repository.WorkoutCustomRepository;
import com.qualle.truegain.repository.WorkoutRepository;
import com.qualle.truegain.service.ExerciseService;
import com.qualle.truegain.service.WorkoutService;
import com.qualle.truegain.service.basic.AbstractService;
import com.qualle.truegain.service.mapper.ExerciseMapper;
import com.qualle.truegain.service.mapper.GenericMapper;
import com.qualle.truegain.service.mapper.SimpleWorkoutMapper;
import com.qualle.truegain.service.mapper.WorkoutMapper;
import com.qualle.truegain.service.util.DateFormatUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutServiceImpl extends AbstractService<Workout, WorkoutDto, Long> implements WorkoutService {

    private final WorkoutRepository repository;
    private final WorkoutCustomRepository customRepository;
    private final WorkoutMapper workoutMapper;
    private final ExerciseMapper exerciseMapper;
    private final SimpleWorkoutMapper simpleWorkoutMapper;
    private final ExerciseService exerciseService;

    @Override
    protected CrudRepository<Workout, Long> getRepository() {
        return repository;
    }

    @Override
    protected GenericMapper<Workout, WorkoutDto> getMapper() {
        return workoutMapper;
    }

    public WorkoutDto getById(Long id) {

        Workout workout = repository.findByIdWithRecords(id);

        WorkoutDto dto = getMapper().toDto(workout, List.of("records"));

        List<ExerciseDto> exercises = dto.getExercises();

        List<WorkoutVolumeDto> volumeForExercises = exercises.stream().map(e -> WorkoutVolumeDto.builder()
                        .name(e.getName())
                        .value(e.getRecords().stream().mapToDouble(r -> r.getReps() * r.getWeight()).sum()).build())
                .collect(Collectors.toList());

        List<WorkoutVolumeDto> volumeForBodyParts = new ArrayList<>(volumeForExercises);
        dto.setVolumeForBodyParts(volumeForBodyParts); // todo

        volumeForExercises.add(0, new WorkoutVolumeDto("Overall", volumeForExercises.stream().mapToDouble(WorkoutVolumeDto::getValue).sum()));
        dto.setVolumeForExercises(volumeForExercises);

        return dto;
    }

    @Override
    public WorkoutDto getByUserIdAndDate(long userId, String date) {
        LocalDate localDate = DateFormatUtil.toDate(date).toLocalDate();
        LocalDateTime dateStart = localDate.atStartOfDay();
        LocalDateTime dateEnd = LocalDateTime.of(localDate, LocalTime.MAX);

        Workout workout = repository.findByUserIdAndDate(userId, dateStart, dateEnd);

        if (workout != null) {
            return workoutMapper.toDto(workout, List.of("records"));
        }

        Workout newWorkout = Workout.builder()
                .date(DateFormatUtil.toDate(date))
                .user(User.builder().id(userId).build())
                .build();

        repository.save(newWorkout);

        return workoutMapper.toDto(newWorkout);
    }

    @Override
    public List<SimpleWorkoutDto> getByUserId(long userId) {

        List<Workout> workouts = repository.findAllByUserId(userId);

        List<SimpleWorkoutDto> dtos = simpleWorkoutMapper.toDto(workouts);

        return dtos;
    }

    @Override
    public List<SimpleWorkoutDto> getRecentByUserIdWithLimit(long userId, int count) {
        List<Workout> workouts = customRepository.findRecentByUserIdWithLimit(userId, count);

        List<SimpleWorkoutDto> dtos = simpleWorkoutMapper.toDto(workouts);

        return dtos;
    }

    @Override
    public Map<Integer, Integer> getCountByUserIdGroupByWeekNumber(long userId) {
        List<Map<String, Number>> workoutsWeekList = repository.findWorkoutCountByUserIdGroupByWeekNumber(userId);

        Map<Integer, Integer> result = new HashMap<>();

        for (Map<String, Number> week : workoutsWeekList) {
            result.put(week.get("week").intValue(), week.get("count").intValue());
        }

        for (int i = 1; i <= 52; i++) {
            result.putIfAbsent(i, 0);
        }
        return result;
    }

    @Override
    public void save(WorkoutDto dto) {
        Workout workout = workoutMapper.fromDto(dto, List.of("records"));

        repository.save(workout);
    }
}
