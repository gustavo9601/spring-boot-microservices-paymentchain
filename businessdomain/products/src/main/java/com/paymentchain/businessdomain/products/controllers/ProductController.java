package com.paymentchain.businessdomain.products.controllers;

import com.paymentchain.businessdomain.products.config.PropertiesConfig;
import com.paymentchain.businessdomain.products.entities.Product;
import com.paymentchain.businessdomain.products.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PropertiesConfig propertiesConfig;

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);


    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        log.info("Environment title: {}", this.propertiesConfig.getEnvironmentTitle());
        return ResponseEntity.ok(this.productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.productRepository.findById(id).orElse(null));
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return ResponseEntity.ok(this.productRepository.save(product));
    }

}
