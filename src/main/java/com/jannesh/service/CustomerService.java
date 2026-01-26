package com.jannesh.service;

import com.jannesh.dto.customer.CreateCustomerRequestDTO;
import com.jannesh.dto.customer.CreateCustomerResponseDTO;
import com.jannesh.entity.customer.Customer;
import com.jannesh.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepo;
    private final ModelMapper modelMapper;

    public CreateCustomerResponseDTO createCustomer(CreateCustomerRequestDTO requestDTO) {
        Customer customer = modelMapper.map(requestDTO, Customer.class);
        return modelMapper.map(customerRepo.save(customer), CreateCustomerResponseDTO.class);
    }
}
