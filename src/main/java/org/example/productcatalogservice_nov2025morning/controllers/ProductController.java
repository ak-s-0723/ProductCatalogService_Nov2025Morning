package org.example.productcatalogservice_nov2025morning.controllers;

import org.example.productcatalogservice_nov2025morning.dtos.CategoryDto;
import org.example.productcatalogservice_nov2025morning.dtos.ProductDto;
import org.example.productcatalogservice_nov2025morning.exceptions.ProductNotFoundException;
import org.example.productcatalogservice_nov2025morning.models.Category;
import org.example.productcatalogservice_nov2025morning.models.Product;
import org.example.productcatalogservice_nov2025morning.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    //@Qualifier("storageProductService")
    private IProductService productService;

//    @Autowired
//    @Qualifier("fakeStoreProductService")
//    private IProductService productService2;

    @GetMapping
    List<ProductDto> getAllProducts() {
        List<ProductDto> productDtos = new ArrayList<>();
      List<Product>products = productService.getAllProducts();
      if (products != null) {
          for(Product product : products) {
              ProductDto productDto = from(product);
              productDtos.add(productDto);
          }
          return productDtos;
      }

      return null;
    }


    @GetMapping("{id}")
    ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {
        if (productId < 0) {
           throw new IllegalArgumentException("Invalid Id passed");
            //return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if(productId == 0) {
            throw new IllegalArgumentException("Please pass product id > 0");
        }


        Product product = productService.getProductById(productId);
        if (product == null) {
            throw new ProductNotFoundException("product not found");
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProductDto productDto = from(product);
        return new ResponseEntity<>(productDto,HttpStatus.OK);
    }

    @PostMapping
    ProductDto createProduct(@RequestBody
                          ProductDto productDto) {

        //return productDto;
        Product input = from(productDto);
        Product product = productService.createProduct(input);
        if(product !=null) {
            return from(product);
        } else {
            throw new RuntimeException("creation failed as product existed already");
        }
    }

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Long id) {
        boolean result = productService.deleteProduct(id);
        if(!result) {
            throw new ProductNotFoundException("product not available");
        }
    }

   @PutMapping("{productId}")
    ProductDto replaceProduct(@PathVariable Long productId,
                              @RequestBody ProductDto productDto) {
        Product product = from(productDto);
        Product response = productService.replaceProduct(product,productId);

        if(response != null) {
            return from(response);
        }

        throw new ProductNotFoundException("product not available");
    }


    private Product from(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setName(productDto.getCategory().getName());
            category.setId(productDto.getCategory().getId());
            product.setCategory(category);
        }
        return product;
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
