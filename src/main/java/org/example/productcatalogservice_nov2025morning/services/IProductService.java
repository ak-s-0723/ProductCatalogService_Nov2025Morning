package org.example.productcatalogservice_nov2025morning.services;

import org.example.productcatalogservice_nov2025morning.models.Product;

import java.util.List;

public interface IProductService {

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product createProduct(Product input);

    Product replaceProduct(Product input,Long id);
}
