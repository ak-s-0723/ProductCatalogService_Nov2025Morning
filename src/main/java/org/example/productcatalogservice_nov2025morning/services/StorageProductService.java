package org.example.productcatalogservice_nov2025morning.services;

import org.example.productcatalogservice_nov2025morning.models.Product;
import org.example.productcatalogservice_nov2025morning.models.State;
import org.example.productcatalogservice_nov2025morning.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class StorageProductService implements IProductService{

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product getProductById(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        return productOptional.orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product createProduct(Product input) {
        Optional<Product> productOptional = productRepo.findById(input.getId());
        if(productOptional.isEmpty()) {
            return productRepo.save(input);
        } else {
            //Throw an exception also
            return null;
        }
    }

    @Override
    public Product replaceProduct(Product input, Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if(productOptional.isPresent()) {
           input.setId(id);
           input.setCreatedAt(productOptional.get().getCreatedAt());
           return productRepo.save(input);
        } else {
            //We can throw an exception here also
            return null;
        }
    }

    @Override
    public boolean deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if(optionalProduct.isPresent())  {
            Product product = optionalProduct.get();
            if(product.getState().equals(State.ACTIVE)) {
                product.setState(State.DELETED);
                productRepo.save(product);
            } else {
                productRepo.deleteById(id);
            }
            return true;
        }

        return false;
    }
}
