package com.vardhan.productservice.dtos;

import com.vardhan.productservice.models.Category;
import com.vardhan.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {

    private long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;

    public Product toProduct() {

        Product product = new Product();
        product.setId(id);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);
        product.setName(title);
        // Create category
        Category category1 = new Category();
        category1.setName(category);

        product.setCategory(category1);
        return product;
    }
}
