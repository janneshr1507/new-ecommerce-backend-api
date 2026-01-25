package com.jannesh.service;

import com.jannesh.dto.vendor.CreateVendorRequestDTO;
import com.jannesh.dto.vendor.CreateVendorResponseDTO;
import com.jannesh.entity.vendor.Vendor;
import com.jannesh.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VendorService {
    private final VendorRepository vendorRepo;
    private final ModelMapper modelMapper;

    public CreateVendorResponseDTO createVendor(CreateVendorRequestDTO requestDTO) {
        Vendor vendor = modelMapper.map(requestDTO, Vendor.class);
        return modelMapper.map(vendorRepo.save(vendor), CreateVendorResponseDTO.class);
    }
}
