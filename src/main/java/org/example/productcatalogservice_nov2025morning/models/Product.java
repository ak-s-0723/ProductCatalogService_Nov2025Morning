package org.example.productcatalogservice_nov2025morning.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product extends BaseModel {
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private Category category;
    private Boolean isPrimeSaleSpecific;
}
