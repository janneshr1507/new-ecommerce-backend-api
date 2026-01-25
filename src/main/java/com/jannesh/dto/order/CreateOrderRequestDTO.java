package com.jannesh.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CreateOrderRequestDTO {
    private Long customerId;
    private List<ItemRequest> itemRequestList;
}
