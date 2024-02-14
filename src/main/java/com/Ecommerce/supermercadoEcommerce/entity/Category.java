package com.Ecommerce.supermercadoEcommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import com.Ecommerce.supermercadoEcommerce.entity.Product;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private String type;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> productList;
}
