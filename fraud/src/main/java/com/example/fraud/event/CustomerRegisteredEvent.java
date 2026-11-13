package com.example.fraud.event;

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
//Why do we need the same fields?
//Customer sends a JSON message containing these four fields.
// Fraud needs a Java class with matching field names and compatible types so Jackson can convert that JSON into a Java object