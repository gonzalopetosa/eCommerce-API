package com.Ecommerce.supermercadoEcommerce.controller;

import com.Ecommerce.supermercadoEcommerce.entity.Order;
import com.Ecommerce.supermercadoEcommerce.entity.OrderItem;
import com.Ecommerce.supermercadoEcommerce.entity.Product;
import com.Ecommerce.supermercadoEcommerce.service.product.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ProductServiceImpl service;

    private List<OrderItem> orderItems = new ArrayList<>();

    private Order o = new Order();

    @GetMapping("")
    public String home(Model model) throws Exception {
        model.addAttribute("productos", service.findAll());
        return "user/home";
    }

    @GetMapping("productoDetalle/{id}")
    public String home(@PathVariable Integer id,Model model) throws Exception {
        Product p = service.findById(id);
        model.addAttribute("producto",p);
        return "user/productHome";
    }

    @PostMapping("/order")
    public String addOrder(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) throws Exception {
        OrderItem orderItem = new OrderItem();
        Product p = service.findById(id);
        double sumTotal = 0;

        orderItem.setOrder(o);
        orderItem.setQuantity(cantidad);
        orderItem.setPrice(p.getPrice()*cantidad);
        orderItem.setProduct(p);
        orderItem.setName(p.getName());

        Integer idProducto= p.getID();
        boolean ingresado = orderItems.stream().anyMatch(product -> product.getProduct().getID()==idProducto);

        if (!ingresado) {
            orderItems.add(orderItem);
        }

        for (OrderItem oI :orderItems){
            sumTotal += oI.getPrice();
        }

        o.setTotal(sumTotal);
        o.setOrderItem(orderItems);

        model.addAttribute("orderItems", orderItems);
        model.addAttribute("order", o);
        return "user/carrito";
    }

    @GetMapping("/delete/product/{id}")
    public String deleteProductOrder(@PathVariable Integer id,Model model){

        List<OrderItem> newOrderItems = new ArrayList<>();
        double sumTotal = 0;
        for (OrderItem oI :orderItems){
            if(oI.getProduct().getID() != id){
                newOrderItems.add(oI);
            }
        }
        orderItems = newOrderItems;
        o.setOrderItem(orderItems);
        for (OrderItem oI :orderItems){
            sumTotal += oI.getPrice();
        }
        o.setTotal(sumTotal);

        model.addAttribute("orderItems", orderItems);
        model.addAttribute("order", o);
        return "user/carrito";
    }
    @GetMapping("/getOrder")
    public String getOrder(Model model){

        model.addAttribute("orderItems", orderItems);
        model.addAttribute("order", o);

        return "user/carrito";
    }

    @GetMapping("/detalleOrden")
    public String viewOrderDetails(){
        return "user/resumenOrden";
    }

}
