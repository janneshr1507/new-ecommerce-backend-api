package com.jannesh.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class CreateCustomerRequestDTO {
    @NotBlank(message = "Customer name is required")
    private String name;

    @NotBlank(message = "Contact number is required")
    @Size(min = 10, max = 10, message = "Contact number must be 10 digits")
    private String contact;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
}
