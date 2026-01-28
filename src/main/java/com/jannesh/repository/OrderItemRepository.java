package com.jannesh.repository;

import com.jannesh.entity.orderitem.OrderItem;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByVendor_VendorId(Long vendorId, Sort sort);
}
