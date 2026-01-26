package com.jannesh.controller;

import com.jannesh.dto.product.CreateProductRequestDTO;
import com.jannesh.dto.product.CreateProductResponseDTO;
import com.jannesh.entity.product.Product;
import com.jannesh.service.ProductService;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
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
    public CreateProductResponseDTO createProduct(@RequestBody CreateProductRequestDTO requestDTO) {
        return productService.createProduct(requestDTO);
    }

    @PostMapping("/create-bulk")
    public List<CreateProductResponseDTO> createProduct(@RequestBody List<CreateProductRequestDTO> requestDTOList) {
        return productService.createProduct(requestDTOList);
    }
}
