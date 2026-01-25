package com.jannesh.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateProductResponseDTO {
    private Long vendorId;
    private Long productId;
    private String name;
    private int quantity;
    private float price;
}
