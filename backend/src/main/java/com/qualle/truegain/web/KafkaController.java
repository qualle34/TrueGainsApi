package com.qualle.truegain.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class KafkaController {


    private final KafkaTemplate<Object, Object> template;


    @GetMapping("/kafka/send")
    public void send(){
        template.send("test", "world");
    }


}
