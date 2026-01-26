package com.jannesh.controller;

import com.jannesh.dto.customer.CreateCustomerRequestDTO;
import com.jannesh.dto.customer.CreateCustomerResponseDTO;
import com.jannesh.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/create")
    public CreateCustomerResponseDTO createCustomer(@RequestBody @Valid CreateCustomerRequestDTO requestDTO) {
        return customerService.createCustomer(requestDTO);
    }
}
