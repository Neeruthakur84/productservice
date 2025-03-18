package com.vardhan.productservice.dtos;

import com.vardhan.productservice.models.Category;
import com.vardhan.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {

    private long id;
    private String name;
    private double price;
    private String description;
    private String imageUrl;
    private String category;


    public static ProductResponseDto from(Product product) {

        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setImageUrl(product.getImageUrl());
        dto.setCategory(product.getCategory().getName());
        return dto;

    }
}
