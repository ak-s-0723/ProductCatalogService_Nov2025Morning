package org.example.productcatalogservice_nov2025morning.controllers;

import org.example.productcatalogservice_nov2025morning.dtos.SearchRequestDto;
import org.example.productcatalogservice_nov2025morning.models.Product;
import org.example.productcatalogservice_nov2025morning.services.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ISearchService searchService;


    @PostMapping
    public Page<Product> searchProducts(@RequestBody SearchRequestDto searchRequestDto) {
        return searchService.searchProducts(searchRequestDto.getQuery(), searchRequestDto.getPageSize(), searchRequestDto.getPageNumber(),searchRequestDto.getSortParamList());
    }
}


//{
//        "query" : "laptop",
//        "pageSize" : 6,
//        "pageNumber" : 0,
//        "sortParamList" : [
//        {
//        "paramName" : "price",
//        "sortType" : "ASC"
//        },
//        {
//        "paramName" : "id",
//        "sortType" : "DESC"
//        }
//        ]
//        }