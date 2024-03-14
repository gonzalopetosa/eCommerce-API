package com.Ecommerce.supermercadoEcommerce.controller;

import com.Ecommerce.supermercadoEcommerce.entity.Client;
import com.Ecommerce.supermercadoEcommerce.service.client.ClientServiceImpl;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.net.URI;


@RestController
public class ClientController {

    @Autowired
    private ClientServiceImpl service;

    @PostMapping("/client")
    public ResponseEntity<?> save(@RequestBody Client c){
        try {
            if(c.getID()==null)
                return ResponseEntity.created(new URI("/inventario/"+c.getID())).body(service.add(c));
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"No se permite crear clientes con ID\\\"}");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error. Por favor intente mas tarde.\\\"}");
        }
    }

    @GetMapping("/clients")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error. Por favor intente mas tarde.\\\"}");
        }
    }
    @GetMapping("/client/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        try {
            if(service.exist(id))
                return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\\\"error\\\":\\\"No existe cliente Con ID: \\\"" + id + "}");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error. Por favor intente mas tarde.\\\"}");
        }
    }

    @GetMapping("/client/orders/{id}")
    public ResponseEntity<?> getOrderHistoryById(@PathVariable Integer id){
        try {
            if(service.exist(id))
                return ResponseEntity.status(HttpStatus.OK).body(service.findOrderHistoryById(id));
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\\\"error\\\":\\\"No existe cliente Con ID: \\\"" + id + "}");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error. Por favor intente mas tarde.\\\"}");
        }
    }

    @PutMapping("client/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Client c){
        try {
            if(service.exist(id))
                return ResponseEntity.status(HttpStatus.OK).body(service.modify(id,c));
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\\\"error\\\":\\\"No existe cliente Con ID: \\\"" + id + "}");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error. Por favor intente mas tarde.\\\"}");
        }
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try {
            if(service.exist(id)) {
                service.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\\\"error\\\":\\\"No existe cliente Con ID: \\\"" + id + "}");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error. Por favor intente mas tarde.\\\"}");
        }
    }
}
