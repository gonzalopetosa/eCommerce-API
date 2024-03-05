package com.Ecommerce.supermercadoEcommerce.service.order;

import java.util.Date;

import com.Ecommerce.supermercadoEcommerce.entity.Client;
import com.Ecommerce.supermercadoEcommerce.entity.Order;
import com.Ecommerce.supermercadoEcommerce.entity.OrderItem;

import java.util.List;

public interface OrderService {
    public Order add(Order order) throws Exception;
    public List<Order> findAll() throws Exception;
    public Order findById(Integer id) throws Exception;
    public Order modify(Integer id, Order order) throws Exception;
    public void deleteById(Integer Id) throws Exception;
    public Date findDateByID(Integer id) throws Exception;
    public Client findClientById(Integer orderId) throws Exception;
    public List<OrderItem> findItemsById(Integer id) throws Exception;
    public boolean exist(Integer id);
    public String generarNumeroOrden() throws Exception;
}
