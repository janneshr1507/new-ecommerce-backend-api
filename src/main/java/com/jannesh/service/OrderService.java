package com.jannesh.service;

import com.jannesh.dto.order.CreateOrderResponseDTO;
import com.jannesh.dto.order.ItemRequest;
import com.jannesh.dto.order.ItemResponse;
import com.jannesh.entity.customer.Customer;
import com.jannesh.entity.order.OrderStatus;
import com.jannesh.entity.orderitem.OrderItem;
import com.jannesh.entity.orderitem.OrderItemStatus;
import com.jannesh.entity.product.Product;
import com.jannesh.entity.product.ProductStatus;
import com.jannesh.repository.CustomerRepository;
import com.jannesh.entity.order.Order;
import com.jannesh.dto.order.CreateOrderRequestDTO;
import com.jannesh.repository.OrderItemRepository;
import com.jannesh.repository.OrderRepository;
import com.jannesh.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepo;
    private final OrderItemRepository orderItemRepo;
    private final CustomerRepository customerRepo;
    private final ProductRepository productRepo;
    private final ModelMapper modelMapper;

    @Transactional()
    public CreateOrderResponseDTO createOrder(CreateOrderRequestDTO requestDTO) {

        /*Step 1: Check Customer Existence*/
        Optional<Customer> optionalCustomer = customerRepo.findById(requestDTO.getCustomerId());
        if(optionalCustomer.isEmpty()) throw new EntityNotFoundException("Customer Not Found");
        Customer customer = optionalCustomer.get();

        /*Step 2: Check Item List, if empty throwing error*/
        List<ItemRequest> itemRequestList = requestDTO.getItemRequestList();
        if(itemRequestList.isEmpty()) throw new RuntimeException("Order List is Empty");

        /*Step 3: Order Initiated (because needed orderId for future transactions)*/
        Order order = modelMapper.map(requestDTO, Order.class);
        order.setOrderId(null);
        order.setCustomer(customer);
        order.setStatus(OrderStatus.INITIATED);
        Order savedOrder = orderRepo.save(order);

        /*Step 4: Preparing Response*/
        CreateOrderResponseDTO response = new CreateOrderResponseDTO();
        List<ItemResponse> itemResponseList = new ArrayList<>();

        int totalOrders = 0;
        int totalConfirmedOrders = 0;
        for(ItemRequest item: itemRequestList) {
            totalOrders++;
            OrderItem orderItem = new OrderItem();

            /*Step 5: Check Product Existence*/
            Optional<Product> optionalProduct = productRepo.findById(item.getProductId());
            if(optionalProduct.isEmpty()) throw new EntityNotFoundException("Product Not Found");

            /*Step 6: Confirming the OrderItem*/
            Product product = optionalProduct.get();
            orderItem.setProduct(product);
            orderItem.setOrder(savedOrder);
            orderItem.setName(product.getName());
            modelMapper.map(item, orderItem);

            /*Check Product Quantity Availability*/
            if(product.getQuantity() >= item.getQuantity() && product.getStatus() == ProductStatus.ACTIVE) {
                product.setQuantity(product.getQuantity() - item.getQuantity());
                productRepo.save(product);
                orderItem.setStatus(OrderItemStatus.CONFIRMED);
                totalConfirmedOrders++;
            } else if(product.getQuantity() < item.getQuantity() && product.getStatus() == ProductStatus.ACTIVE) {
                orderItem.setStatus(OrderItemStatus.OUT_OF_STOCK);
            } else orderItem.setStatus(OrderItemStatus.DISCARDED);

            ItemResponse itemResponse = modelMapper.map(orderItemRepo.save(orderItem), ItemResponse.class);
            itemResponseList.add(itemResponse);
        }

        response.setCustomerId(customer.getCustomerId());

        /*Step 7: Confirming the Order based on confirmed orders count*/
        if(totalOrders == totalConfirmedOrders) savedOrder.setStatus(OrderStatus.CONFIRMED);
        else if(totalConfirmedOrders == 0) savedOrder.setStatus(OrderStatus.CANCELLED);
        else savedOrder.setStatus(OrderStatus.PARTIAL_CONFIRMED);

        Order resavedOrder = orderRepo.save(savedOrder);
        response.setOrderId(resavedOrder.getOrderId());

        response.setItemResponseList(itemResponseList);

        return response;
    }
}
