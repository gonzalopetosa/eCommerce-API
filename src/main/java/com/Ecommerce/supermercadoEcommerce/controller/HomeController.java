package com.Ecommerce.supermercadoEcommerce.controller;

import com.Ecommerce.supermercadoEcommerce.entity.Product;
import com.Ecommerce.supermercadoEcommerce.service.product.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ProductServiceImpl service;

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
    public String addOrder(){
        return "user/carrito";
    }
}
