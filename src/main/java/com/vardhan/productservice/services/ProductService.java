package com.vardhan.productservice.services;

import com.vardhan.productservice.exceptions.ProductNotFoundException;
import com.vardhan.productservice.models.Category;
import com.vardhan.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product createProduct(String name, double price, String description, String imageUrl, String category) throws ProductNotFoundException;
}
