package com.vardhan.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreRequestDto {
    private String title;
    private String description;
    private String category;
    private double price;
    private String image;
}
