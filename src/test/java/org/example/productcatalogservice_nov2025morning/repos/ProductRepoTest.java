package org.example.productcatalogservice_nov2025morning.repos;

import jakarta.transaction.Transactional;
import org.example.productcatalogservice_nov2025morning.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;


    @Test
    @Transactional
    public void testRepoMethods() {
        //List<Product> productList = productRepo.findAllByOrderByPrice();
        //System.out.println(productList.get(0).getName());
        //System.out.println(productList.get(productList.size()-1).getName());
        System.out.println(productRepo.getProductNameByProductId(33L));
    }

}