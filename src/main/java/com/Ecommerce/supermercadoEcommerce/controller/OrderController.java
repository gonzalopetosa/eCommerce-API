package com.Ecommerce.supermercadoEcommerce.controller;

import com.Ecommerce.supermercadoEcommerce.entity.Client;
import com.Ecommerce.supermercadoEcommerce.entity.Order;
import com.Ecommerce.supermercadoEcommerce.service.client.ClientServiceImpl;
import com.Ecommerce.supermercadoEcommerce.service.order.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.net.URI;

@RestController
public class OrderController {

    @Autowired
    private OrderServiceImpl service;
    @Autowired
    private ClientServiceImpl clientService;

    @PostMapping("/order/{clientId}")
    public ResponseEntity<?> save(@PathVariable Integer clientId,@RequestBody Order o){
        try {
            if(!clientService.exist(clientId))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"El cliente no existe\\\"}");
            if (o.getID() == null) {
                Client client = clientService.findById(clientId);
                o.setClient(client);
                Order newOrder = service.add(o);
                client.getOrder_history().add(o);
                clientService.add(client);
                return ResponseEntity.created(new URI("/order/" + o.getID())).body(newOrder);
            }
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"No se permite crear Order con ID\\\"}");

        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error. Por favor intente mas tarde.\\\"}");
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok(service.findAll());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error. Por favor intente mas tarde.\\\"}");
        }
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        try {
            if (service.exist(id)) {
                Order order = service.findById(id);
                return ResponseEntity.ok(order);
            }
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\\\"error\\\":\\\"No existe order Con ID: \\\"" + id + "}");
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error. Por favor intente mas tarde.\\\"}");
        }
    }

    @GetMapping("/order/date/{id}")
    public ResponseEntity<?> getDateById(@PathVariable Integer id){
        try {
            if (service.exist(id))
                return ResponseEntity.ok(service.findDateByID(id));
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\\\"error\\\":\\\"No existe order Con ID: \\\"" + id + "}");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error. Por favor intente mas tarde.\\\"}");
        }
    }
    @GetMapping("/order/client/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Integer id){
        try {
            if (service.exist(id))
                return ResponseEntity.ok(service.findClientById(id));
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\\\"error\\\":\\\"No existe order Con ID: \\\"" + id + "}");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error. Por favor intente mas tarde.\\\"}");
        }
    }

    @GetMapping("/order/items/{id}")
    public ResponseEntity<?> getItemsById(@PathVariable Integer id){
        try {
            if (service.exist(id))
                return ResponseEntity.ok(service.findItemsById(id));
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\\\"error\\\":\\\"No existe order Con ID: \\\"" + id + "}");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error. Por favor intente mas tarde.\\\"}");
        }
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<?> modify(@PathVariable Integer id, @RequestBody Order o){
        try {
            if(o.getClient()==null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"El campo cliente no puede ser null\\\"}");
            }
            if (service.exist(id))
                return ResponseEntity.ok(service.modify(id,o));
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\\\"error\\\":\\\"No existe order Con ID: \\\"" + id + "}");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error. Por favor intente mas tarde.\\\"}");
        }
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try {
            if (service.exist(id)){
                service.deleteById(id);
                return ResponseEntity.noContent().build();
            }
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\\\"error\\\":\\\"No existe order Con ID: \\\"" + id + "}");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\\\"error\\\":\\\"Error. Por favor intente mas tarde.\\\"}");
        }
    }
}
