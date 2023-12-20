package com.qualle.truegain.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RabbitController {


    private final RabbitTemplate rabbitTemplate;

    @GetMapping("/rabbit/send")
    public void send(){
        rabbitTemplate.convertAndSend("testExchange", "hello", "world");
    }

    @RabbitListener(queues = "queue1")
    public void processMyQueue(String message) {
        System.out.printf("Received from myQueue : %s ", new String(message.getBytes()));
    }

}
