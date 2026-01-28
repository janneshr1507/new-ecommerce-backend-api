package com.jannesh.controller;

import com.jannesh.dto.product.CreateProductRequestDTO;
import com.jannesh.dto.product.ProductResponseDTO;
import com.jannesh.dto.product.UpdateProductRequestDTO;
import com.jannesh.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ProductResponseDTO createProduct(@RequestBody CreateProductRequestDTO requestDTO) {
        return productService.createProduct(requestDTO);
    }

    @PostMapping("/create-bulk")
    public List<ProductResponseDTO> createProduct(@RequestBody List<CreateProductRequestDTO> requestDTOList) {
        return productService.createProduct(requestDTOList);
    }

    @PostMapping("/update")
    public ProductResponseDTO updateProductDetails(@RequestBody UpdateProductRequestDTO requestDTO) {
        return productService.updateProductDetails(requestDTO);
    }
}
