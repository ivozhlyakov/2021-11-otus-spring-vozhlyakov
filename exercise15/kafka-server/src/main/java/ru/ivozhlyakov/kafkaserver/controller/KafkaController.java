package ru.ivozhlyakov.kafkaserver.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ivozhlyakov.kafkaserver.service.KafkaProducerService;

@RestController
@AllArgsConstructor
public class KafkaController {

    private KafkaProducerService service;

    @PostMapping("/addMessage")
    public void addMessage(@RequestParam(name = "text") String text) {
        service.sendMessage("kafka-test", text);
    }

}
