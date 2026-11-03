
package com.example.Customer.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> producerConfig() {
        //We are creating a method that returns a Map.We are creating a method that returns a Map.
        //In our case, instead of storing personal information(key:City	value:Kathmandu), we store Kafka configuration

        Map<String, Object> config = new HashMap<>();
 //Create an empty Map . String: Our keys will be Strings.

        //Object: Values can be different types of objects.
        //put() adds a key-value pair to our Map and The put() method accepts two parameters
        //1.The key (which configuration are we setting?).
        //2. The value (what should that configuration be?).
        //this config 1. BOOTSTRAP_SERVERS_CONFIG – Specifies the Kafka broker address
        // so the producer knows where to connect
        config.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers
        );
        //KEY_SERIALIZER_CLASS_CONFIG – Specifies how to convert
        // the message key from a Java object (String) into bytes.
        //here converted into String since  StringSerializer.class

        config.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class
        );
//VALUE_SERIALIZER_CLASS_CONFIG – Specifies how to convert the message
// value from a Java object (String) into bytes.
       //here converted into String since  StringSerializer.class
        config.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class
        );

        return config;
    }

    @Bean//@Bean    Tells Spring to manage the object returned by this method as a Spring bean.
    public ProducerFactory<String, String> producerFactory() {//ProducerFactory is an interface provided by Spring Kafka that is responsible for creating Kafka producers.
        return new DefaultKafkaProducerFactory<>(producerConfig());
        //DefaultKafkaProducerFactory
        // It creates Kafka producer instances using the configuration we provide.
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        //KafkaTemplate is a Spring Kafka class that provides convenient methods for sending messages to Kafka.
        return new KafkaTemplate<>(producerFactory());
    }/*
    - new KafkaTemplate<>() → Creates a new KafkaTemplate object.
    - producerFactory() → Calls our previous method to get the ProducerFactory.
    Why do we pass ProducerFactory?
    Because KafkaTemplate needs a ProducerFactory to create and manage the Kafka producers that actually send messages.
    */
}
