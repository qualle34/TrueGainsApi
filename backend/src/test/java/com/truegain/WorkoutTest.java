package com.truegain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.truegain.model.entity.Exercise;
import com.truegain.model.entity.Record;
import com.truegain.model.entity.User;
import com.truegain.model.entity.Workout;
import com.truegain.repository.WorkoutRepository;
import com.truegain.service.WorkoutService;
import com.truegain.service.util.DateFormatUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.yml")
public class WorkoutTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private WorkoutService workoutService;

    @MockBean
    private WorkoutRepository workoutRepository;

    @BeforeAll
    public void before() {

        LocalDateTime date1 = DateFormatUtil.toDate("2023-09-23T10:03:44.9503845");
//        LocalDateTime date2 = DateFormatUtil.toDate("2023-09-23T00:00");
//        LocalDateTime date3 = DateFormatUtil.toDate("2023-09-23T23:59:59.9999999");

        List<Record> records = new ArrayList<>();

        records.add(Record.builder()
                .id(1)
                .exercise(Exercise.builder().id(1).build())
                .weight(20)
                .reps(10)
                .build());

        records.add(Record.builder()
                .id(2)
                .exercise(Exercise.builder().id(1).build())
                .weight(40)
                .reps(8)
                .build());

        records.add(Record.builder()
                .id(3)
                .exercise(Exercise.builder().id(2).build())
                .weight(60)
                .reps(6)
                .build());

        Workout workout = Workout.builder()
                .id(1)
                .user(User.builder().id(1).name("Henry").build())
                .date(date1)
                .records(records)
                .build();

        List<Workout> workouts = new ArrayList<>();

        workouts.add(workout);


        given(workoutRepository.findById(eq(1L))).willReturn(Optional.of(workout));
        given(workoutRepository.findByUserIdAndDate(eq(1L), any(LocalDateTime.class), any(LocalDateTime.class))).willReturn(workout);
        given(workoutRepository.findAllByUserId(eq(1L))).willReturn(workouts);
        given(workoutRepository.save(any(Workout.class))).willReturn(workout);
    }

    public void getWorkoutByIdWhenCorrectDataThenSuccess() throws Exception {

        mvc.perform(get("/workout/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)));
    }

    public void getWorkoutByIdWhenIdWasNullThenFail() throws Exception {

        mvc.perform(get("/workout")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type", is("BAD_REQUEST")));
    }

    public void getWorkoutByUserIdWhenWhenCorrectDataThenSuccess() throws Exception {

        mvc.perform(get("/workout/simple?userId=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id", is(1)));
    }

    public void getWorkoutByUserIdWhenIdWasNullThenFail() throws Exception {

        mvc.perform(get("/workout/simple")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type", is("BAD_REQUEST")));
    }

    public void getWorkoutByUserIdAndDateWhenCorrectDataThenSuccess() throws Exception {

        mvc.perform(get("/workout?userId=1&date=2023-09-23T10:03:44.9503845")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)));
    }

    public void getWorkoutByUserIdAndDateWhenWorkoutIsNullThenFail() throws Exception {

        mvc.perform(get("/workout/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type", is("NOT_FOUND")));
    }

    public void getWorkoutByUserIdAndDateWhenUserIsNullThenFail() throws Exception {

        mvc.perform(get("/workout?date=2023-09-23T10:03:44.9503845")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type", is("BAD_REQUEST")));
    }

    public void getWorkoutByUserIdAndDateWhenDateWasNullThenFail() throws Exception {

        mvc.perform(get("/workout?userId=2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type", is("BAD_REQUEST")));
    }

    public void saveWorkoutWhenCorrectDataThenSuccess() throws Exception {

        LocalDateTime date1 = DateFormatUtil.toDate("2023-09-23T10:03:44.9503845");

        List<Record> records = new ArrayList<>();

        records.add(Record.builder()
                .id(1)
                .exercise(Exercise.builder().id(1).build())
                .weight(20)
                .reps(10)
                .build());

        records.add(Record.builder()
                .id(2)
                .exercise(Exercise.builder().id(1).build())
                .weight(40)
                .reps(8)
                .build());

        records.add(Record.builder()
                .id(3)
                .exercise(Exercise.builder().id(2).build())
                .weight(60)
                .reps(6)
                .build());

        Workout workout = Workout.builder()
                .id(1)
                .user(User.builder().id(1).name("Henry").build())
                .date(date1)
                .records(records)
                .build();


        mvc.perform(post("/workout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(workout)))
                .andExpect(status().isOk());
    }

    public void saveWorkoutWhenDataWasNullThenFail() throws Exception {

        mvc.perform(post("/workout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type", is("BAD_REQUEST")));
    }
}