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

    private List<Order> orders;

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
        orders = new ArrayList<Order>();
        Product p = service.findById(id);
        Order o = new Order();
        o.setTotal(p.getPrice()*cantidad);
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        OrderItem oItem = OrderItem.builder().order(o).product(p).quantity(cantidad).build();
        orderItems.add(oItem);
        o.setOrderItem(orderItems);
        orders.add(o);

        model.addAttribute("producto",p);
        model.addAttribute("orden",o);
        return "user/carrito";
    }
}
