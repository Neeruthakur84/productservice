package com.vardhan.productservice.services;

import com.vardhan.productservice.dtos.FakeStoreProductDto;
import com.vardhan.productservice.dtos.FakeStoreRequestDto;
import com.vardhan.productservice.exceptions.ProductNotFoundException;
import com.vardhan.productservice.models.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class FakeStoreProductServiceTest {

    RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
    FakeStoreProductService fsp = new FakeStoreProductService(restTemplate);

    @Test
    public void testGetProductByIdReturnsProduct() throws ProductNotFoundException {

        // Arrange
        FakeStoreProductDto dummyFakeStoreProductDto = new FakeStoreProductDto();
        dummyFakeStoreProductDto.setId(1);
        dummyFakeStoreProductDto.setCategory("category");
        dummyFakeStoreProductDto.setDescription("description");
        dummyFakeStoreProductDto.setPrice(1.0);
        dummyFakeStoreProductDto.setTitle("title");
        dummyFakeStoreProductDto.setImage("image.url");

        when(restTemplate.getForObject("https://fakestoreapi.com/products/1", FakeStoreProductDto.class))
                .thenReturn(dummyFakeStoreProductDto);

        // Act

       Product product = fsp.getProductById(1L);

        // Assert

        assertEquals(1, product.getId());
        assertEquals("category", product.getCategory().getName());
        assertEquals("description", product.getDescription());
        assertEquals(1.0, product.getPrice());
        assertEquals("image.url", product.getImageUrl());
        assertEquals("title", product.getName());
    }


    @Test
    public void testGetProductByIdWithNullProductThrowingException() throws ProductNotFoundException {
        // Arrange

        when(restTemplate.getForObject("https://fakestoreapi.com/products/1", FakeStoreProductDto.class)).thenReturn(null);

        //Act
       // Product product = fsp.getProductById(1L);


        //ACT and Assert
        assertThrows(ProductNotFoundException.class, () -> fsp.getProductById(1L));

    }

    @Test
    public void testCreateProductReturnsProductWithId() throws ProductNotFoundException {
    //Arrange
        FakeStoreProductDto dummyFakeStoreProductDto = new FakeStoreProductDto();
        dummyFakeStoreProductDto.setId(1);
        dummyFakeStoreProductDto.setCategory("category");
        dummyFakeStoreProductDto.setDescription("description");
        dummyFakeStoreProductDto.setPrice(1.0);
        dummyFakeStoreProductDto.setTitle("title");
        dummyFakeStoreProductDto.setImage("image.url");

        // This will not work , since fakeStoreRequestDto object will not be same that we get from ACT part. Since a new object is being created in actual object
//        FakeStoreRequestDto fakeStoreRequestDto = new FakeStoreRequestDto();
//        fakeStoreRequestDto.setCategory("category");
//        fakeStoreRequestDto.setDescription("description");
//        when(restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreRequestDto, FakeStoreProductDto.class)).thenReturn(dummyFakeStoreProductDto)

        when(restTemplate.postForObject(eq("https://fakestoreapi.com/products"),
                    any(),
                eq(FakeStoreProductDto.class)))
                .thenReturn(dummyFakeStoreProductDto);

        // ACT
        //String name, double price, String description, String imageUrl, String category
        Product product = fsp.createProduct("title", 1.0, "description", "image.url",
                "category");

        //Assert
        assertEquals(1, product.getId());
        assertEquals("category", product.getCategory().getName());
        assertEquals("description", product.getDescription());
        assertEquals(1.0, product.getPrice());
        assertEquals("image.url", product.getImageUrl());
        assertEquals("title", product.getName());


    }


    @Test
    public void testCreateProductWithVerifyApiCalls () throws ProductNotFoundException {
        //Arrange
        FakeStoreProductDto dummyFakeStoreProductDto = new FakeStoreProductDto();
        dummyFakeStoreProductDto.setId(1);
        dummyFakeStoreProductDto.setCategory("category");
        dummyFakeStoreProductDto.setDescription("description");
        dummyFakeStoreProductDto.setPrice(1.0);
        dummyFakeStoreProductDto.setTitle("title");
        dummyFakeStoreProductDto.setImage("image.url");

        // This will not work , since fakeStoreRequestDto object will not be same that we get from ACT part. Since a new object is being created in actual object
//        FakeStoreRequestDto fakeStoreRequestDto = new FakeStoreRequestDto();
//        fakeStoreRequestDto.setCategory("category");
//        fakeStoreRequestDto.setDescription("description");
//        when(restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreRequestDto, FakeStoreProductDto.class)).thenReturn(dummyFakeStoreProductDto)

        when(restTemplate.postForObject(eq("https://fakestoreapi.com/products"),
                any(),
                eq(FakeStoreProductDto.class)))
                .thenReturn(dummyFakeStoreProductDto);

        // ACT
        //String name, double price, String description, String imageUrl, String category
        Product product = fsp.createProduct("title", 1.0, "description", "image.url",
                "category");

        //Assert
        verify(restTemplate, times(2)).postForObject(eq("https://fakestoreapi.com/products"),
                any(),
                eq(FakeStoreProductDto.class));



    }
}