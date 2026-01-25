package com.jannesh.service;

import com.jannesh.dto.product.CreateProductRequestDTO;
import com.jannesh.dto.product.CreateProductResponseDTO;
import com.jannesh.entity.product.Product;
import com.jannesh.entity.vendor.Vendor;
import com.jannesh.repository.ProductRepository;
import com.jannesh.repository.VendorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepo;
    private final VendorRepository vendorRepo;
    private final ModelMapper modelMapper;

    public CreateProductResponseDTO createProduct(CreateProductRequestDTO requestDTO) {
        if(requestDTO.getVendorId() == null) throw new RuntimeException("VendorId in the request cannot be null");

        Optional<Vendor> optionalVendor = vendorRepo.findById(requestDTO.getVendorId());
        if(optionalVendor.isEmpty()) throw new EntityNotFoundException("Vendor Not Found");

        Vendor vendor = optionalVendor.get();
        Product product = modelMapper.map(requestDTO, Product.class);
        product.setProductId(null);
        product.setVendor(vendor);

        return modelMapper.map(productRepo.save(product), CreateProductResponseDTO.class);
    }
}
