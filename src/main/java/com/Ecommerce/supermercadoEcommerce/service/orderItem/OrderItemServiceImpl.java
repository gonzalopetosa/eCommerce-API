package com.Ecommerce.supermercadoEcommerce.service.orderItem;

import com.Ecommerce.supermercadoEcommerce.entity.OrderItem;
import com.Ecommerce.supermercadoEcommerce.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService{

    @Autowired
    public OrderItemRepository repository;

    @Override
    public OrderItem save(OrderItem orderItem) {
        return repository.save(orderItem);
    }
}
