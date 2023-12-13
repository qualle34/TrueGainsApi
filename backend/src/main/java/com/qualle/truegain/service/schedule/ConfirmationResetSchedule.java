package com.qualle.truegain.service.schedule;

import com.qualle.truegain.config.property.ConfirmationProperties;
import com.qualle.truegain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConfirmationResetSchedule {

    private final UserRepository repository;
    private final ConfirmationProperties properties;

    @Scheduled(cron = "0 0 1 * * MON")
    @Transactional
    public void scheduleFixedDelayTask() {
        log.info("Delete old confirmations");

        LocalDateTime date = LocalDateTime.now().minusSeconds(properties.getLifetime().getSeconds());

        repository.deleteConfirmationAfterDate(date);
    }
}
