package com.jannesh.dto.product;

import com.jannesh.entity.product.ProductStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class UpdateProductRequestDTO {
    private Long productId;
    private String name;
    private int quantity;
    private float price;
    private ProductStatus status;
}
