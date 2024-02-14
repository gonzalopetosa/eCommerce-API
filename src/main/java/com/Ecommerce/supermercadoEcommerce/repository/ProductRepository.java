package com.Ecommerce.supermercadoEcommerce.repository;

import com.Ecommerce.supermercadoEcommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
