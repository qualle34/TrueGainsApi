package com.qualle.truegain;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.*;
import java.time.format.DateTimeFormatter;

@SpringBootTest
public class QuickTest {

    public void test() {

        String date = "2023-09-29T10:03:44.9503845";

        LocalDate localDate = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME).toLocalDate();

        LocalDateTime dateStart = localDate.atStartOfDay();
        LocalDateTime dateEnd = LocalDateTime.of(localDate, LocalTime.MAX);
    }
}
