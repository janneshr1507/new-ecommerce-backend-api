package com.jannesh.service;

import com.jannesh.entity.customer.Customer;
import com.jannesh.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepo;

    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }
}
