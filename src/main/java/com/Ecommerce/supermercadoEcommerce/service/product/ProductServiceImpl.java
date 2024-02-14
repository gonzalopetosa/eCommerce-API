package com.Ecommerce.supermercadoEcommerce.service.product;

import com.Ecommerce.supermercadoEcommerce.entity.Category;
import com.Ecommerce.supermercadoEcommerce.entity.Product;
import com.Ecommerce.supermercadoEcommerce.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Override
    public Product add(Product producto) {
        return repository.save(producto);
    }

    @Override
    public List<Product> findAll() throws Exception {
        try {
            return repository.findAll();
        }
        catch (Exception e){
            throw new Exception(e.getCause());
        }
    }

    @Override
    public Product findById(Integer id) throws Exception {
        try {
            Optional<Product> optionalProduct = repository.findById(id);
            if(optionalProduct.isPresent())
                return optionalProduct.get();
            else
                throw new Exception("No existe Producto Con ID: "+id);
        }
        catch (Exception e){
            throw new Exception(e.getCause());
        }
    }

    @Override
    public void modify(Product producto) throws Exception {
        try {
            repository.save(producto);
        }
        catch (Exception e){
            throw new Exception(e.getCause());
        }
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        try {
                repository.deleteById(id);
        }
        catch (Exception e){
            throw new Exception(e.getCause());
        }


    }

    @Override
    public int findStockById(Integer id) throws Exception {
        try {
            Optional<Product> optionalProduct = repository.findById(id);
            if(optionalProduct.isPresent())
                return optionalProduct.get().getStock();
            else
                throw new Exception("No existe Producto Con ID: "+id);
        }
        catch (Exception e){
            throw new Exception(e.getCause());
        }
    }

    @Override
    public double findPriceById(Integer id) throws Exception {
        try {
            Optional<Product> optionalProduct = repository.findById(id);
            if(optionalProduct.isPresent())
                return optionalProduct.get().getPrice();
            else
                throw new Exception("No existe Producto Con ID: "+id);
        }
        catch (Exception e){
            throw new Exception(e.getCause());
        }
    }

    @Override
    public Category findCategoryById(Integer id) throws Exception {
        try {
            Optional<Product> optionalProduct = repository.findById(id);
            if(optionalProduct.isPresent())
                return optionalProduct.get().getCategory();
            else
                throw new Exception("No existe Producto Con ID: "+id);
        }
        catch (Exception e){
            throw new Exception(e.getCause());
        }
    }

    @Override
    public boolean exist(Integer id) {
        return repository.existsById(id);
    }
}
