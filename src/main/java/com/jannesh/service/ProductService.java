package com.jannesh.service;

import com.jannesh.dto.order.ItemRequest;
import com.jannesh.dto.product.CreateProductRequestDTO;
import com.jannesh.dto.product.ProductResponseDTO;
import com.jannesh.dto.product.UpdateProductRequestDTO;
import com.jannesh.entity.product.Product;
import com.jannesh.entity.product.ProductStatus;
import com.jannesh.entity.vendor.Vendor;
import com.jannesh.repository.ProductRepository;
import com.jannesh.repository.VendorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepo;
    private final VendorRepository vendorRepo;
    private final ModelMapper modelMapper;

    public ProductResponseDTO createProduct(CreateProductRequestDTO requestDTO) {
        if(requestDTO.getVendorId() == null) throw new RuntimeException("VendorId in the request cannot be null");

        Optional<Vendor> optionalVendor = vendorRepo.findById(requestDTO.getVendorId());
        if(optionalVendor.isEmpty()) throw new EntityNotFoundException("Vendor Not Found");

        Vendor vendor = optionalVendor.get();

        /*Updating product already exists or else creating new*/
        Product product = productRepo.findByName(requestDTO.getName())
                .map(existingProduct -> {
                    existingProduct.setQuantity(existingProduct.getQuantity() + requestDTO.getQuantity());
                    return existingProduct;
                })
                .orElseGet(() -> {
                    Product newProduct = new Product();
                    newProduct.setVendor(vendor);
                    newProduct.setName(requestDTO.getName());
                    newProduct.setQuantity(requestDTO.getQuantity());
                    newProduct.setPrice(requestDTO.getPrice());
                    return newProduct;
                });

        return modelMapper.map(productRepo.save(product), ProductResponseDTO.class);
    }

    @Transactional
    public List<ProductResponseDTO> createProduct(List<CreateProductRequestDTO> requestDTOList) {
        if(requestDTOList.isEmpty()) throw new RuntimeException("Product List is Empty");
        List<ProductResponseDTO> response = new ArrayList<>();
        for(CreateProductRequestDTO requestDTO: requestDTOList) {
                response.add(createProduct(requestDTO));
        }
        return response;
    }

    @Transactional
    public void updateProductQuantity(List<ItemRequest> itemRequestList) {

        for(ItemRequest itemRequest: itemRequestList) {
            Optional<Product> optionalProduct = productRepo.findById(itemRequest.getProductId());
            if(optionalProduct.isEmpty()) throw new EntityNotFoundException("Product Not Found");

            Product product = optionalProduct.get();

            if(product.getQuantity() == 0){
                product.setStatus(ProductStatus.INACTIVE);
                productRepo.save(product);
            }
        }
    }

    @Transactional
    public ProductResponseDTO updateProductDetails(UpdateProductRequestDTO requestDTO) {
        Optional<Product> optionalProduct = productRepo.findById(requestDTO.getProductId());
        if(optionalProduct.isEmpty()) throw new EntityNotFoundException("Product Not Found");
        Product product = optionalProduct.get();

        if(!Objects.equals(requestDTO.getName(), product.getName())) {
            product.setName(requestDTO.getName());
        }

        if(!Objects.equals(requestDTO.getPrice(), product.getPrice())) {
            product.setPrice(requestDTO.getPrice());
        }
        if(!Objects.equals(requestDTO.getQuantity(),product.getQuantity())) {
            product.setQuantity(product.getQuantity());
        }
        if(requestDTO.getQuantity() > 0) product.setStatus(ProductStatus.ACTIVE);

        return modelMapper.map(productRepo.save(product), ProductResponseDTO.class);
    }
}
