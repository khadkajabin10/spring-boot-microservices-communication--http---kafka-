package com.example.Customer.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    @NotBlank(message = "First name is required")
   private String firstname;

    @NotBlank(message = "Last name is required")
    private String lastname;
    @NotNull(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;
}
