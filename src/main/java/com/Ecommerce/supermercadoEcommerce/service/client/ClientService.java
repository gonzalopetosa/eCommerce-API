package com.Ecommerce.supermercadoEcommerce.service.client;

import com.Ecommerce.supermercadoEcommerce.entity.Client;
import com.Ecommerce.supermercadoEcommerce.entity.Order;


import java.util.List;

public interface ClientService {

    public Client add(Client producto) throws Exception;
    public List<Client> findAll() throws Exception;
    public Client findById(Integer id) throws Exception;
    public Client modify(Integer id, Client producto) throws Exception;
    public void deleteById(Integer Id) throws Exception;
    public List<Order> findOrderHistoryById(Integer id) throws Exception;
    public boolean exist(Integer ID);
    public Client findByEmail(String email);
}
