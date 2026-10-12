package com.example.Customer.controller;

import com.example.Customer.Dto.CustomerDto;
import com.example.Customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping
    public void customerRegister(@Valid @RequestBody CustomerDto customerDto){
        log.info("Customer is register: {}",customerDto);
        customerService.registerCustomer(customerDto);

    }
    @GetMapping("/{id}")
    public CustomerDto getCustomerById(@PathVariable("id") Long id) {

        return customerService.getCustomerById(id);
    }
}
