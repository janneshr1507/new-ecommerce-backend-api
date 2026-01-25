package com.jannesh.dto.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class ItemRequest {
    private Long productId;
    private String name;
    private int quantity;
    private float price;
}
