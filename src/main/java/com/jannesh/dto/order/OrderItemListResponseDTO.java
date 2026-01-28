package com.jannesh.dto.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
public class OrderItemListResponseDTO {
    private Long vendorId;
    private List<ItemResponse> itemResponseList;
}
