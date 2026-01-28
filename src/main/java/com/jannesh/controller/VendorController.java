package com.jannesh.controller;

import com.jannesh.dto.order.OrderItemListResponseDTO;
import com.jannesh.dto.vendor.CreateVendorRequestDTO;
import com.jannesh.dto.vendor.CreateVendorResponseDTO;
import com.jannesh.service.OrderService;
import com.jannesh.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendor")
@RequiredArgsConstructor
public class VendorController {
    private final VendorService vendorService;
    private final OrderService orderService;

    @PostMapping("/create")
    public CreateVendorResponseDTO createVendor(@RequestBody CreateVendorRequestDTO requestDTO) {
        return vendorService.createVendor(requestDTO);
    }

    @GetMapping("/fetch-order-item/{vendorId}")
    public OrderItemListResponseDTO fetchOrderItemDetailsByVendorId(@PathVariable Long vendorId) {
        return orderService.fetchOrderDetailsByVendorId(vendorId);
    }
}
