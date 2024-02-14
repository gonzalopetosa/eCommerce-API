package com.Ecommerce.supermercadoEcommerce.service.product;

import com.Ecommerce.supermercadoEcommerce.entity.Category;
import com.Ecommerce.supermercadoEcommerce.entity.Product;
import java.util.List;

public interface ProductService {

    public Product add(Product producto) throws Exception;
    public List<Product> findAll() throws Exception;
    public Product findById(Integer id) throws Exception;
    public void modify(Product producto) throws Exception;
    public void deleteById(Integer Id) throws Exception;
    public int findStockById(Integer id) throws Exception;
    public double findPriceById(Integer id) throws Exception;
    public Category findCategoryById(Integer id) throws Exception;
    public boolean exist(Integer ID);

}
