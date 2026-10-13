package com.example.fraud.controller;

import com.example.fraud.dot.FraudCheckResponsedto;
import com.example.fraud.service.FraudCheckService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraudCheck")
@AllArgsConstructor
public class FraudController {
    private final FraudCheckService fraudCheckService;

    @GetMapping("{customerId}")
    public FraudCheckResponsedto isFraudster(@PathVariable("customerId") Long customerId){
        boolean isFraudCustomer= fraudCheckService.isFraudster(customerId);
        return  new FraudCheckResponsedto(isFraudCustomer);
    }
}
