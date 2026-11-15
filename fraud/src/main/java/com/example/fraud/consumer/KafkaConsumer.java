package com.example.fraud.consumer;

import com.example.fraud.entity.Fraud;
import com.example.fraud.event.CustomerRegisteredEvent;
import com.example.fraud.repository.FraudRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor

public class KafkaConsumer {
    //Inject FraudRepository into KafkaConsumer
    private final FraudRepository fraudRepository;

    @KafkaListener(topics = "customer-registered", groupId = "fraud-group")
    public void consume(CustomerRegisteredEvent event) {

        System.out.println("Message received by Fraud Service:");
        System.out.println("Customer ID: " + event.getCustomerId());
        System.out.println("First Name: " + event.getFirstname());
        System.out.println("Last Name: " + event.getLastname());
        System.out.println("Email: " + event.getEmail());
//we sucessfull receive message or event from consumer Now we need to save the received event's information into MongoDB.
        //to do
        // Receive the event
        //Create a Fraud object from the received event
        Fraud fraud = Fraud.builder()
                .customerId(event.getCustomerId())
                .isFraudster(false)
                .createdAt(LocalDateTime.now())
                .build();
        fraudRepository.save(fraud);

        //    // Save it into MongoDB
    }
}