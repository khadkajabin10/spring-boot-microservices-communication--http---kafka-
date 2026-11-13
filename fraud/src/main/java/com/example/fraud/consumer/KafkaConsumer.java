package com.example.fraud.consumer;

import com.example.fraud.event.CustomerRegisteredEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "customer-registered", groupId = "fraud-group")
    public void consume(CustomerRegisteredEvent event) {

        System.out.println("Message received by Fraud Service:");
        System.out.println("Customer ID: " + event.getCustomerId());
        System.out.println("First Name: " + event.getFirstname());
        System.out.println("Last Name: " + event.getLastname());
        System.out.println("Email: " + event.getEmail());

    }
}