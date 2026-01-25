package com.jannesh.controller;

import com.jannesh.dto.order.CreateOrderResponseDTO;
import com.jannesh.entity.order.Order;
import com.jannesh.service.OrderService;
import com.jannesh.dto.order.CreateOrderRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    public CreateOrderResponseDTO createOrder(@RequestBody CreateOrderRequestDTO requestDTO) {
        return orderService.createOrder(requestDTO);
    }
}
