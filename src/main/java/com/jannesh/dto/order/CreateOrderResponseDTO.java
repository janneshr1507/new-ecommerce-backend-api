package com.jannesh.dto.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
public class CreateOrderResponseDTO {
    private Long customerId;
    private Long orderId;
    private List<ItemResponse> itemResponseList;
}
