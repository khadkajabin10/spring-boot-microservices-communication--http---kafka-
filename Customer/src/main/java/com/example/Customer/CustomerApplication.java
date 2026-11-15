package com.example.Customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}
	/*
	@Bean
	public CommandLineRunner commandLineRunner(	KafkaTemplate<String, String> kafkaTemplate) {
//It is a Spring Boot interface that allows us to execute code automatically after the application starts.
		//Spring automatically injects the KafkaTemplate bean we created in KafkaProducerConfig.
		return args -> {
			//This is a lambda expression. It contains the code that runs when Spring Boot starts.
			kafkaTemplate.send(
					"customer-registered",
					"Hello how are you!"
			);

			System.out.println("Message sent to Kafka!");
		};
	}
*/
}
