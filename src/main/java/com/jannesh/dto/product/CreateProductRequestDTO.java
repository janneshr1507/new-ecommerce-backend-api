package com.jannesh.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateProductRequestDTO {
    private Long vendorId;
    private String name;
    private int quantity;
    private float price;
}
