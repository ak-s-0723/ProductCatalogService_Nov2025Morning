package org.example.productcatalogservice_nov2025morning.repos;

import org.example.productcatalogservice_nov2025morning.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

    Optional<Category> findById(Long id);
}
