package com.example.Customer.service;

import com.example.Customer.Dto.CustomerDto;
import com.example.Customer.Repository.CustomerRepository;
import com.example.Customer.config.ModelMapperConfig;
import com.example.Customer.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    public void registerCustomer(CustomerDto customerDto) {
        Customer customer= Customer.builder()
                .firstname(customerDto.getFirstname())
                .lastname(customerDto.getLastname())
                .email(customerDto.getEmail())
                .build();
        customerRepository.save(customer);
    }
    public CustomerDto getCustomerById(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Customer not found with ID: " + id
                ));



        CustomerDto customerDto =
                modelMapper.map(customer, CustomerDto.class);



        return customerDto;
    }
}
