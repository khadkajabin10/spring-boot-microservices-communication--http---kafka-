package com.example.fraud.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "customer-registered", groupId = "fraud-group")
    public void consume(String message) {

        System.out.println("Message received by Fraud Service: " + message);

    }
}