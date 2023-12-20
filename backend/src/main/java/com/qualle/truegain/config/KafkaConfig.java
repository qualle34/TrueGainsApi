package com.qualle.truegain.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topic() {
        return new NewTopic("test", 1, (short) 1);
    }

    @KafkaListener(id = "1", topics = "test")
    public void listen(String data) {
        log.info("Received: " + data);
    }

}
