package com.truegain;

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



//        System.out.println(currentDate);
    }
}
