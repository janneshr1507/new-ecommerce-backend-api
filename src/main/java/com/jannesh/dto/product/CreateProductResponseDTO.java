package com.jannesh.dto.product;

import com.jannesh.entity.product.ProductStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateProductResponseDTO {
    private Long vendorId;
    private Long productId;
    private String name;
    private int quantity;
    private float price;
    private ProductStatus status;
}
