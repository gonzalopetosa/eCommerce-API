package com.Ecommerce.supermercadoEcommerce.repository;

import com.Ecommerce.supermercadoEcommerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
}
