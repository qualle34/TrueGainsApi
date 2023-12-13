package com.qualle.truegain.service.impl;

import com.qualle.truegain.api.*;
import com.qualle.truegain.api.support.ErrorType;
import com.qualle.truegain.model.entity.User;
import com.qualle.truegain.model.entity.Workout;
import com.qualle.truegain.model.entity.Workout_;
import com.qualle.truegain.model.entity.custom.LoadDistributionByCategories;
import com.qualle.truegain.model.exception.BadRequestException;
import com.qualle.truegain.repository.ExerciseRepository;
import com.qualle.truegain.repository.WorkoutRepository;
import com.qualle.truegain.repository.WorkoutSpecificationsRepository;
import com.qualle.truegain.repository.specifications.WorkoutSpecifications;
import com.qualle.truegain.service.WorkoutService;
import com.qualle.truegain.service.basic.AbstractService;
import com.qualle.truegain.service.mapper.GenericMapper;
import com.qualle.truegain.service.mapper.SimpleWorkoutMapper;
import com.qualle.truegain.service.mapper.WorkoutMapper;
import com.qualle.truegain.service.util.DateFormatUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutServiceImpl extends AbstractService<Workout, WorkoutDto, Long> implements WorkoutService {

    private final WorkoutRepository repository;
    private final WorkoutSpecificationsRepository specificationsRepository;
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
    public WorkoutDto getByIdAndUser(long id, long userId) {
        WorkoutDto dto = getById(id);

        if (dto.getUserId() != userId) {
            throw new BadRequestException("Unable to load workout. Workout id is not valid", ErrorType.BAD_REQUEST);
        }

        return dto;
    }

    @Override
    public WorkoutDto getByUserIdAndDate(long userId, String date) {
        LocalDate localDate = DateFormatUtil.toDate(date).toLocalDate();
        LocalDateTime dateStart = localDate.atStartOfDay();
        LocalDateTime dateEnd = LocalDateTime.of(localDate, LocalTime.MAX);

        List<Workout> workouts = specificationsRepository
                .findAll(WorkoutSpecifications.hasUserId(userId)
                        .and(WorkoutSpecifications.isBetweenDates(dateStart, dateEnd)));

        if (!workouts.isEmpty()) {
            return workoutMapper.toDto(workouts.get(0), List.of("records"));
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

        Pageable pageable = PageRequest.of(0, count, Sort.by(Sort.Direction.DESC, Workout_.DATE));

        List<Workout> workouts = specificationsRepository.findAll(WorkoutSpecifications.hasUserId(userId), pageable).toList();

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
    public MuscleDistributionChartDto getMuscleDistributionChartData(long userId) {
        List<LoadDistributionByCategories> workoutsThisMonth = repository.findLoadByCategoryByUserId(userId);

        if (workoutsThisMonth == null || workoutsThisMonth.isEmpty()) {
            return null;
        }

        Map<String, Float> thisMonthData = workoutsThisMonth.stream().collect(Collectors
                .toMap(LoadDistributionByCategories::getName, LoadDistributionByCategories::getLoad));

        return MuscleDistributionChartDto.builder()
                .thisMonthData(thisMonthData)
                .build();
    }

    @Override
    public int getWorkoutCountByUserId(long userId) {
        return repository.countByUserId(userId);
    }

    @Override
    public float getTotalLoadByUserId(Long id) {
        return repository.findTotalLoadByUserId(id).getLoad();
    }

    @Override
    @Transactional
    public void updateWorkoutForUser(WorkoutDto dto, long userId) {
        Workout workout = repository.findByIdWithRecords(dto.getId());

        if (workout.getUser().getId() != userId) {
            throw new BadRequestException("Unable to update workout. Workout id is not valid", ErrorType.BAD_REQUEST);
        }

        Workout newWorkout = workoutMapper.fromDto(dto, List.of("records"));

        repository.save(newWorkout);
    }

    @Override
    @Transactional
    public void deleteWorkoutForUser(long id, long userId) {
        Workout workout = repository.findByIdWithRecords(id);

        if (workout.getUser().getId() != userId) {
            throw new BadRequestException("Unable to delete workout. Workout id is not valid", ErrorType.BAD_REQUEST);
        }

        repository.delete(workout);
    }
}
