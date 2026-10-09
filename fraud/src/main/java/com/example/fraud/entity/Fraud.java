package com.example.fraud.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "fraud")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fraud {
    @Id
    private String id;
    private Long customerId;
    private boolean isFraudster;
    private LocalDateTime createdAt;
}
