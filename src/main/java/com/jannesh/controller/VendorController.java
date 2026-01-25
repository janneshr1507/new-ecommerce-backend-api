package com.jannesh.controller;

import com.jannesh.dto.vendor.CreateVendorRequestDTO;
import com.jannesh.dto.vendor.CreateVendorResponseDTO;
import com.jannesh.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendor")
@RequiredArgsConstructor
public class VendorController {
    private final VendorService vendorService;

    @PostMapping("/create")
    public CreateVendorResponseDTO createVendor(@RequestBody CreateVendorRequestDTO requestDTO) {
        return vendorService.createVendor(requestDTO);
    }
}
