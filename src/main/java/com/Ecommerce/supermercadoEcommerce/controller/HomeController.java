package com.Ecommerce.supermercadoEcommerce.controller;

import com.Ecommerce.supermercadoEcommerce.entity.Client;
import com.Ecommerce.supermercadoEcommerce.entity.Order;
import com.Ecommerce.supermercadoEcommerce.entity.OrderItem;
import com.Ecommerce.supermercadoEcommerce.entity.Product;
import com.Ecommerce.supermercadoEcommerce.service.client.ClientServiceImpl;
import com.Ecommerce.supermercadoEcommerce.service.order.OrderServiceImpl;
import com.Ecommerce.supermercadoEcommerce.service.orderItem.OrderItemServiceImpl;
import com.Ecommerce.supermercadoEcommerce.service.product.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;


@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    OrderItemServiceImpl orderItemService;

    private List<OrderItem> orderItems = new ArrayList<>();

    private Order o = new Order();

    @GetMapping("")
    public String home(Model model) throws Exception {
        model.addAttribute("productos", productService.findAll());
        return "user/home";
    }

    @GetMapping("productoDetalle/{id}")
    public String home(@PathVariable Integer id,Model model) throws Exception {
        Product p = productService.findById(id);
        model.addAttribute("producto",p);
        return "user/productHome";
    }

    @PostMapping("/order")
    public String addOrder(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) throws Exception {
        OrderItem orderItem = new OrderItem();
        Product p = productService.findById(id);
        double sumTotal = 0;

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
    public String viewOrderDetails(Model model) throws Exception {

        Client c = clientService.findById(2);

        model.addAttribute("orderItems", orderItems);
        model.addAttribute("order", o);
        model.addAttribute("client", c);
        return "user/resumenOrden";
    }

    @GetMapping("/saveOrder")
    public String saveOrder() throws Exception {
        Date crationDate = new Date();
        o.setDate(crationDate);
        o.setNumber(orderService.generarNumeroOrden());

        Client c = clientService.findById(2);
        o.setClient(c);

        orderService.add(o);

        for (OrderItem ot : orderItems){
            ot.setOrder(o);
            orderItemService.save(ot);

        }

        o = new Order();
        orderItems.clear();
        return "redirect:/";
    }

}
