package com.example.fraud.service;
import com.example.fraud.entity.Fraud;
import com.example.fraud.repository.FraudRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {

    private final FraudRepository fraudRepository;
    public boolean isFraudster(Long customerId) {
        Fraud fraud = Fraud.builder()
                .customerId(customerId)
                .isFraudster(false)
                .createdAt(LocalDateTime.now())
                .build();

        fraudRepository.save(fraud);

        return false;
    }
}