package com.Ecommerce.supermercadoEcommerce.repository;

import com.Ecommerce.supermercadoEcommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query("SELECT DISTINCT o FROM Order o JOIN FETCH o.client JOIN FETCH o.orderItem")
    List<Order> findAllWithClientAndItems();
    @Query("SELECT o FROM Order o JOIN FETCH o.client JOIN FETCH o.orderItem WHERE o.ID = :id")
    Optional<Order> findByIdWithClientAndItems(@Param("id") Integer id);
}
