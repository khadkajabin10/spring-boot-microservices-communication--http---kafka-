package com.example.Customer.service;

import com.example.Customer.Dto.CustomerDto;
import com.example.Customer.Dto.FraudCheckResponsedto;
import com.example.Customer.Repository.CustomerRepository;
import com.example.Customer.config.ModelMapperConfig;
import com.example.Customer.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;
    public void registerCustomer(CustomerDto customerDto) {
        Customer customer= Customer.builder()
                .firstname(customerDto.getFirstname())
                .lastname(customerDto.getLastname())
                .email(customerDto.getEmail())
                .build();
        customerRepository.saveAndFlush(customer);
        //todo check if fraudter
        FraudCheckResponsedto fraudCheckResponse=restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraudCheck/{customerId}",
                FraudCheckResponsedto.class,//yo class ho respose of this http respest
                customer.getId()//mathi insert vako object ko id
        );

        if (fraudCheckResponse == null) {
            log.error("Fraud microservice returned a null response");
            throw new IllegalStateException(
                    "Fraud check response is null"
            );
        }

        if (fraudCheckResponse.isFraudster()) {//For a primitive boolean field named fraudster, Lombok generates a getter named isFraudster().
            log.warn("Fraudster detected! Customer ID: {}",
                    customer.getId());

            throw new IllegalStateException("Fraudster");
        }

        log.info("Fraud check passed. Customer ID: {}",
                customer.getId());

        log.info("Customer registration completed successfully");



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
