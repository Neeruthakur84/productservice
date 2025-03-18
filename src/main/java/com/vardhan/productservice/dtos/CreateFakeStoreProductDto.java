package com.vardhan.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFakeStoreProductDto {

    private String name;
    private double price;
    private String description;
    private String imageUrl;
    private String category;
}
