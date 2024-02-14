package com.Ecommerce.supermercadoEcommerce.service.order;

import com.Ecommerce.supermercadoEcommerce.entity.Client;
import com.Ecommerce.supermercadoEcommerce.entity.Order;
import com.Ecommerce.supermercadoEcommerce.entity.OrderItem;
import com.Ecommerce.supermercadoEcommerce.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository repository;

    @Override
    public Order add(Order order) throws Exception {
        try {
            return repository.save(order);
        }
        catch (Exception e){
            throw new Exception(e.getCause());
        }
    }

    @Override
    public List<Order> findAll() throws Exception {
        try {
            return repository.findAllWithClientAndItems();
        }
        catch (Exception e){
            throw new Exception(e.getCause());
        }
    }

    @Override
    public Order findById(Integer id) throws Exception {
        try {
            Optional<Order> orderOptionals = repository.findByIdWithClientAndItems(id);
            if(orderOptionals.isPresent()){
                return orderOptionals.get();
            }
            else
                throw new Exception("Order not present");
        }
        catch (Exception e){
            throw new Exception(e.getCause());
        }
    }

    @Override
    public Order modify(Integer id, Order newOrder) throws Exception {
        try {
            Optional<Order> orderOptionals = repository.findById(id);
            if(orderOptionals.isPresent()){
                Order order = orderOptionals.get();
                order.setClient(newOrder.getClient());
                order.setOrderItem(newOrder.getOrderItem());
                order.setDate(newOrder.getDate());
                return repository.save(order);
            }
            else
                throw new Exception("Order not present");
        }
        catch (Exception e){
            throw new Exception(e.getCause());
        }
    }

    @Override
    public void deleteById(Integer Id) throws Exception {
        try {
            repository.deleteById(Id);
        }
        catch (Exception e){
            throw new Exception(e.getCause());
        }
    }

    @Override
    public Date findDateByID(Integer id) throws Exception {
        try {
            Optional<Order> orderOptionals = repository.findById(id);
            if(orderOptionals.isPresent()){
                return orderOptionals.get().getDate();
            }
            else
                throw new Exception("Order not present");
        }
        catch (Exception e){
            throw new Exception(e.getCause());
        }
    }

    @Override
    public Client findClientById(Integer orderId) throws Exception {
        try {
            Optional<Order> orderOptionals = repository.findById(orderId);
            if(orderOptionals.isPresent()){
                return orderOptionals.get().getClient();
            }
            else
                throw new Exception("Order not present");
        }
        catch (Exception e){
            throw new Exception(e.getCause());
        }
    }

    @Override
    public List<OrderItem> findItemsById(Integer id) throws Exception {
        try {
            Optional<Order> orderOptionals = repository.findById(id);
            if(orderOptionals.isPresent()){
                return orderOptionals.get().getOrderItem();
            }
            else
                throw new Exception("Order not present");
        }
        catch (Exception e){
            throw new Exception(e.getCause());
        }
    }
    @Override
    public boolean exist(Integer ID) {
        return repository.existsById(ID);
    }
}
