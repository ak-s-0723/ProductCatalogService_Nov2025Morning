package org.example.productcatalogservice_nov2025morning.services;

import org.example.productcatalogservice_nov2025morning.dtos.SortParam;
import org.example.productcatalogservice_nov2025morning.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISearchService {
    Page<Product> searchProducts(String query, Integer pageSize, Integer pageNumber, List<SortParam> sortParamList);
}
