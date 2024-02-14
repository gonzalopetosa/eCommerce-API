package com.Ecommerce.supermercadoEcommerce.controller;

import com.Ecommerce.supermercadoEcommerce.entity.Product;
import com.Ecommerce.supermercadoEcommerce.service.product.ProductServiceImpl;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
public class ProductController {

    @Autowired
    private ProductServiceImpl service;

    @PostMapping("/inventario")
    public ResponseEntity<?> save(@RequestBody Product p) throws Exception{
        try {
            if(p.getID()==null)
                return ResponseEntity.created(new URI("/inventario/"+p.getID())).body(service.add(p));
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"No se permite crear productos con ID\\\"}");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error. Por favor intente mas tarde.\\\"}");
        }
    }

    @GetMapping("/inventario")
    public ResponseEntity<?> getAll() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error. Por favor intente mas tarde.\\\"}");
        }
    }
    @GetMapping("/inventario/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) throws Exception {
        try {
            if(service.exist(id))
                return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\\\"error\\\":\\\"No existe Producto Con ID: \\\"" + id + "}");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error. Por favor intente mas tarde.\\\"}");
        }
    }
    @GetMapping("/inventario/stock/{id}")
    public ResponseEntity<?> getStockById(@PathVariable Integer id){
         try {
             if(service.exist(id))
                 return ResponseEntity.status(HttpStatus.OK).body(service.findStockById(id));
             else
                 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\\\"error\\\":\\\"No existe Producto Con ID: \\\"" + id + "}");
         }
         catch (Exception e){
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error. Por favor intente mas tarde.\\\"}");
         }
    }
    @GetMapping("/inventario/price/{id}")
    public ResponseEntity<?> getPriceById(@PathVariable Integer id){
        try {
            if(service.exist(id))
                return ResponseEntity.status(HttpStatus.OK).body(service.findPriceById(id));
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\\\"error\\\":\\\"No existe Producto Con ID: \\\"" + id + "}");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error. Por favor intente mas tarde.\\\"}");
        }
    }
    @GetMapping("/inventario/category/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id){
        try {
            if(service.exist(id))
                return ResponseEntity.status(HttpStatus.OK).body(service.findCategoryById(id));
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\\\"error\\\":\\\"No existe Producto Con ID: \\\"" + id + "}");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error. Por favor intente mas tarde.\\\"}");
        }
    }
    @PutMapping("/inventario/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,@RequestBody Product p) throws Exception {
        try {
            if(service.exist(id))
                return ResponseEntity.status(HttpStatus.OK).build();
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\\\"error\\\":\\\"No existe Producto Con ID: \\\"" + id + "}");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error. Por favor intente mas tarde.\\\"}");
        }
    }

    @DeleteMapping("/inventario/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) throws Exception{
        try {
            if(service.exist(id)) {
                service.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\\\"error\\\":\\\"No existe Producto Con ID: \\\"" + id + "}");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error. Por favor intente mas tarde.\\\"}");
        }
    }
}
