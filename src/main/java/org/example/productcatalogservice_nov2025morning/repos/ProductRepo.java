package org.example.productcatalogservice_nov2025morning.repos;

import org.example.productcatalogservice_nov2025morning.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

     Optional<Product> findById(Long id);

     List<Product> findAll();

     Product save(Product product);

     void deleteById(Long id);

     //want to get product data sorted by price

     //get Category whose product id = n;
}
