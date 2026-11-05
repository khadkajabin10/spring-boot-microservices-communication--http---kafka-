package com.example.Customer.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CustomerRegisteredEvent {

    private Long customerId;
    private String firstname;
    private String lastname;
    private String email;
}