package com.vardhan.productservice.controllers;

import com.vardhan.productservice.dtos.ProductResponseDto;
import com.vardhan.productservice.exceptions.ProductNotFoundException;
import com.vardhan.productservice.models.Category;
import com.vardhan.productservice.models.Product;
import com.vardhan.productservice.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @MockitoBean
    @Qualifier("productDbService")
    public ProductService productService;
    @Autowired
    public ProductController productController;

    @Test
    public void testGetProductByIdReturnProductResponseDto() throws ProductNotFoundException {

        // Arrange and Training
        long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("test");
        product.setPrice(21.50);
        product.setDescription("test Description");
        product.setImageUrl("testImage.url");

        Category category = new Category();
        category.setId(1L);
        category.setName("testCategory");
        category.setDescription("Test Category Description");

        product.setCategory(category);

        when(productService.getProductById(productId)).thenReturn(product);

        //ACT

      ProductResponseDto productResponseDto = productController.getProductById(productId);

        //ASSERT
        assertEquals(productId, productResponseDto.getId());
        assertEquals("test", productResponseDto.getName());
        assertEquals("test Description", productResponseDto.getDescription());
        assertEquals("testImage.url", productResponseDto.getImageUrl());
        assertEquals(21.50, productResponseDto.getPrice());
        assertEquals(productId, category.getId());
    }

    @Test
    public void testGetProductByIdReturnsNull() throws ProductNotFoundException {

           when(productService.getProductById(1L)).thenReturn(null);
           ProductResponseDto productResponseDto = productController.getProductById(1L);
           assertNull(productResponseDto);

    }

    public void testGetAllProductsReturnsListOfProductResponseDto() throws ProductNotFoundException {

        //Arrange

        //ACT

        //Assert

    }
}