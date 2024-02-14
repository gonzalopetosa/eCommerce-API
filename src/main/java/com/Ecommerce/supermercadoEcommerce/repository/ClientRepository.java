package com.Ecommerce.supermercadoEcommerce.repository;

import com.Ecommerce.supermercadoEcommerce.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByEmail(String email);
}
