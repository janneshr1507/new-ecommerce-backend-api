package com.jannesh.dto.customer;

import com.jannesh.entity.customer.CustomerStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateCustomerResponseDTO {
    private Long customerId;
    private String name;
    private String contact;
    private String email;
    private CustomerStatus status;
}
