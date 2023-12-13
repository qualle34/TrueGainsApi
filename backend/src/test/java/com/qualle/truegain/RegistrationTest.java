package com.qualle.truegain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qualle.truegain.api.ConfirmRegistrationDto;
import com.qualle.truegain.api.NewRegistrationDto;
import com.qualle.truegain.api.TemporaryTokenDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Testcontainers
@ActiveProfiles("test-containers-flyway")
@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.yml")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @Order(1)
    public void registerNewUserWhenCorrectDataThenSuccess() throws Exception {

        var registration = NewRegistrationDto.builder()
                .name("User")
                .login("testuser")
                .email("testuser@gmail.com")
                .password("testuser")
                .build();

        var response = mvc.perform(post("/registration/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(registration)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token", any(String.class)))
                .andReturn().getResponse();

        TemporaryTokenDto token = mapper.readValue(response.getContentAsString(), TemporaryTokenDto.class);

        var confirmation = ConfirmRegistrationDto.builder()
                .token(token.getToken())
                .code(111111)
                .build();

        mvc.perform(post("/registration/confirm")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(confirmation)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.accessToken", any(String.class)))
                .andExpect(jsonPath("$.refreshToken", any(String.class)));
    }

    @Test
    @Order(2)
    public void registerNewUserWhenLoginExistedInDbThenFail() throws Exception {

        var registration = NewRegistrationDto.builder()
                .name("User")
                .login("testuser")
                .email("test@gmail.com")
                .password("testuser")
                .build();

        mvc.perform(post("/registration/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(registration)))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type", any(String.class)));

    }

    @Test
    @Order(3)
    public void registerNewUserWhenEmailExistedInDbThenFail() throws Exception {

        var registration = NewRegistrationDto.builder()
                .name("User")
                .login("test")
                .email("testuser@gmail.com")
                .password("testuser")
                .build();

        mvc.perform(post("/registration/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(registration)))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type", any(String.class)));

    }

    @Test
    @Order(4)
    public void registerNewUserWhenPasswordIsWeekThenFail() throws Exception {

        var registration = NewRegistrationDto.builder()
                .name("User")
                .login("newtestuser")
                .email("newtestuser@gmail.com")
                .password("a")
                .build();

        mvc.perform(post("/registration/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(registration)))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type", any(String.class)));

    }

    @Test
    @Order(5)
    public void registerNewUserWhenConfirmationCodeIsNotValidThenFail() throws Exception {

        var registration = NewRegistrationDto.builder()
                .name("User")
                .login("newtestuser")
                .email("newtestuser@gmail.com")
                .password("newtestuser")
                .build();

        var response = mvc.perform(post("/registration/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(registration)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token", any(String.class)))
                .andReturn().getResponse();

        TemporaryTokenDto token = mapper.readValue(response.getContentAsString(), TemporaryTokenDto.class);

        var confirmation = ConfirmRegistrationDto.builder()
                .token(token.getToken())
                .code(222222)
                .build();

        mvc.perform(post("/registration/confirm")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(confirmation)))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type", any(String.class)));
    }
}
