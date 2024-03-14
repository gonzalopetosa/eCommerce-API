package com.Ecommerce.supermercadoEcommerce.controller;

import com.Ecommerce.supermercadoEcommerce.entity.Client;
import com.Ecommerce.supermercadoEcommerce.service.client.ClientServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping("/register")
    public String create(){
        return "user/registro";
    }

    @PostMapping("/save")
    public String save(Client client) throws Exception {
        LOGGER.info("Usuario a registrar {}", client);
        client.setTipo("USER");
        clientService.add(client);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

}
