package com.jannesh.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemResponse {
    private Long orderItemId;
    private Long productId;
    private String name;
    private int quantity;
    private float price;
}
