package com.example.fraud.config;

import com.example.fraud.event.CustomerRegisteredEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> consumerConfig() {

        Map<String, Object> config = new HashMap<>();

        config.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers
        );

        config.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                "fraud-group"
        );

        config.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class
        );

        config.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JacksonJsonDeserializer.class
        );
        //We want Fraud to use its own CustomerRegisteredEvent class  which is inside fraud instead of customer so below config.
        config.put(
                JacksonJsonDeserializer.VALUE_DEFAULT_TYPE,
                CustomerRegisteredEvent.class.getName()
        );

        config.put(
                JacksonJsonDeserializer.USE_TYPE_INFO_HEADERS,
                false
        );
        //VALUE_DEFAULT_TYPE: Tells Fraud which Java class to create from the incoming JSON.
        //USE_TYPE_INFO_HEADERS = false: Tells Fraud to ignore the producer's class information
        // in the Kafka headers and use its own configured class instead.
        config.put(
                JacksonJsonDeserializer.TRUSTED_PACKAGES,
                "com.example.fraud.event"
        );//This tells the deserializer to trust the package containing our event class when resolving the JSON type information.

        config.put(
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                "earliest"
        );

        return config;
    }
    /*
    BOOTSTRAP_SERVERS_CONFIG	Address of our Kafka broker (localhost:9092).
    GROUP_ID_CONFIG	Identifies our consumer group (fraud-group).
    KEY_DESERIALIZER_CLASS_CONFIG	Converts message keys from bytes into Strings.
    VALUE_DESERIALIZER_CLASS_CONFIG	Converts message values from bytes into Strings.
    AUTO_OFFSET_RESET_CONFIG	Starts from the earliest available message if this group has no committed offset.
     offset measns id, 1 has hellokafka 2 has other message
    */

    @Bean
    public ConsumerFactory<String, CustomerRegisteredEvent> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CustomerRegisteredEvent>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, CustomerRegisteredEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}