package com.example.fraud.repository;


import com.example.fraud.entity.Fraud;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FraudRepository extends MongoRepository<Fraud, String> {
}
