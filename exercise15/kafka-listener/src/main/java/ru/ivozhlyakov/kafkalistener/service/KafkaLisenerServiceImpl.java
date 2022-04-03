package ru.ivozhlyakov.kafkalistener.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaLisenerServiceImpl implements KafkaListenerService {

    @Autowired
    public KafkaLisenerServiceImpl() {
    }

    @Override
    @KafkaListener(topics = "kafka-test", groupId = "kafkaTest")
    public void listenGroupFoo(String message)
    {
        System.out.println("Received Message in group kafkaTest: " + message);
    }
}
