package com.jannesh.controller;

import com.jannesh.dto.customer.CreateCustomerRequestDTO;
import com.jannesh.dto.customer.CreateCustomerResponseDTO;
import com.jannesh.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/create")
    public CreateCustomerResponseDTO createCustomer(@Valid @RequestBody CreateCustomerRequestDTO requestDTO) {
        return customerService.createCustomer(requestDTO);
    }

    @GetMapping("/fetch/{customerId}")
    public CreateCustomerResponseDTO fetchCustomerDetails(@PathVariable Long customerId) {
        return customerService.fetchCustomerDetails(customerId);
    }
}
