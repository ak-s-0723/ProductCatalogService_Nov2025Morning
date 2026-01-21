package org.example.productcatalogservice_nov2025morning.services;

import org.example.productcatalogservice_nov2025morning.dtos.SortParam;
import org.example.productcatalogservice_nov2025morning.dtos.SortType;
import org.example.productcatalogservice_nov2025morning.models.Product;
import org.example.productcatalogservice_nov2025morning.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaSearchService implements ISearchService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Page<Product> searchProducts(String query, Integer pageSize, Integer pageNumber, List<SortParam> sortParams) {
//        Sort sortById = Sort.by("id").descending();
//        Sort sort = Sort.by("price").descending();

        Sort sort =null;

        if(!sortParams.isEmpty()) {
            if (sortParams.get(0).getSortType().equals(SortType.ASC)) {
                sort = Sort.by(sortParams.get(0).getParamName());
            } else {
                sort = Sort.by(sortParams.get(0).getParamName()).descending();
            }
        }

        for(int i=1;i<sortParams.size();i++) {
            if(sortParams.get(i).getSortType().equals(SortType.ASC)) {
                sort = sort.and(Sort.by(sortParams.get(i).getParamName()));
            }else {
                sort = sort.and(Sort.by(sortParams.get(i).getParamName()).descending());
            }
        }

       return productRepo.findByName(query, PageRequest.of(pageNumber,pageSize,sort));
    }
}

//{
//   "price" , "ascending",
//   "rating" , "descending",
//   "id" : "ascensding"
//}