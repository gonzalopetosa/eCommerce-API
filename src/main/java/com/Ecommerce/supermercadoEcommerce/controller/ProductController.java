package com.Ecommerce.supermercadoEcommerce.controller;

import com.Ecommerce.supermercadoEcommerce.entity.Product;
import com.Ecommerce.supermercadoEcommerce.service.UploadFileService;
import com.Ecommerce.supermercadoEcommerce.service.product.ProductServiceImpl;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/productos")
public class ProductController {
    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductServiceImpl service;

    @Autowired
    private UploadFileService upload;

    @GetMapping("")
    public String productos(Model model) throws Exception {
        model.addAttribute("productos",service.findAll());
        return "product/productos";
    }

    @GetMapping("/create")
    public String create(){
        return "product/create";
    }

    @PostMapping("/save")
    public String save(Product p, @RequestParam("img") MultipartFile file) throws IOException {
        LOGGER.info("Se agrego el producto {}",p);

        if (p.getID()==null) {
            String nombreImagen= upload.saveImage(file);
            p.setImage(nombreImagen);
        }else {

        }
        service.add(p);
        return "redirect:/productos";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) throws Exception {
        Product p = service.findById(id);
        model.addAttribute("producto",p);
        return "product/edit";
    }

    @PostMapping("/update")
    public String update(Product p, @RequestParam("img") MultipartFile file) throws Exception {
        LOGGER.info("Se modifica el producto: {}",p);
        if (!file.isEmpty()) {
            if (p.getImage() != null && !p.getImage().equals("default.jpg")) {
                upload.deleteImage(p.getImage());
            }
            String nombreImagen = upload.saveImage(file);
            p.setImage(nombreImagen);
        }
        service.modify(p);
        return "redirect:/productos";
    }



    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) throws Exception {
        Product p = service.findById(id);
        LOGGER.info("Se elimina: {}",p);
        if (p.getImage() != null && !p.getImage().equals("default.jpg")) {
            upload.deleteImage(p.getImage());
        }
        service.deleteById(id);
        return "redirect:/productos";
    }

}
