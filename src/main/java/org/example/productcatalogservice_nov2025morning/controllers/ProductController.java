package org.example.productcatalogservice_nov2025morning.controllers;

import org.example.productcatalogservice_nov2025morning.dtos.CategoryDto;
import org.example.productcatalogservice_nov2025morning.dtos.ProductDto;
import org.example.productcatalogservice_nov2025morning.exceptions.ProductNotFoundException;
import org.example.productcatalogservice_nov2025morning.models.Product;
import org.example.productcatalogservice_nov2025morning.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    List<ProductDto> getAllProducts() {
      List<Product>products = productService.getAllProducts();
      return null;
    }


    @GetMapping("/products/{id}")
    ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {
        if (productId < 1) {
           //throw new IllegalArgumentException("Please pass product id > 0");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Product product = productService.getProductById(productId);
        if (product == null) {
            //throw new ProductNotFoundException("product not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProductDto productDto = from(product);
        return new ResponseEntity<>(productDto,HttpStatus.OK);
    }

    @PostMapping("/products")
    ProductDto createProduct(@RequestBody
                          ProductDto productDto) {
        return productDto;
    }

    private ProductDto from (Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if(product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }

}
