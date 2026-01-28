package com.jannesh.service;

import com.jannesh.dto.customer.CreateCustomerRequestDTO;
import com.jannesh.dto.customer.CreateCustomerResponseDTO;
import com.jannesh.entity.customer.Customer;
import com.jannesh.exception.customer.ContactAlreadyExistsException;
import com.jannesh.exception.customer.EmailAlreadyExistsException;
import com.jannesh.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepo;
    private final ModelMapper modelMapper;

    public CreateCustomerResponseDTO createCustomer(CreateCustomerRequestDTO requestDTO) {

        if(customerRepo.existsByContact(requestDTO.getContact())) throw new ContactAlreadyExistsException("Contact Already Exists");
        if(customerRepo.existsByEmail(requestDTO.getEmail())) throw new EmailAlreadyExistsException("Email Already Exists");

        Customer customer = modelMapper.map(requestDTO, Customer.class);
        return modelMapper.map(customerRepo.save(customer), CreateCustomerResponseDTO.class);
    }

    public CreateCustomerResponseDTO fetchCustomerDetails(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
        if(optionalCustomer.isEmpty()) throw new EntityNotFoundException("Customer Not Found");

        Customer savedCustomer = optionalCustomer.get();
        return modelMapper.map(savedCustomer, CreateCustomerResponseDTO.class);
    }
}
