package ru.ivozhlyakov.kafkaserver.service;

public interface KafkaProducerService {
    void sendMessage(String topicName, String message);
}
