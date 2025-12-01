package org.example.productcatalogservice_nov2025morning.controllers;

import org.example.productcatalogservice_nov2025morning.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    List<Product> getAllProducts() {
        Product product = new Product();
        product.setId(2L);
        product.setName("Apple");
        List<Product> products = new ArrayList<>();
        products.add(product);
        return products;
    }

    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable("id") Long productId) {
        Product product = new Product();
        product.setId(productId);
        product.setName("Iphone");
        return product;
    }

    @PostMapping("/products")
    Product createProduct(@RequestBody
                          Product product) {
        return product;
    }

}
