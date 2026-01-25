package com.jannesh.service;

import com.jannesh.entity.product.Product;
import com.jannesh.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepo;

    public Product createProduct(Product product) {
        return productRepo.save(product);
    }
}
