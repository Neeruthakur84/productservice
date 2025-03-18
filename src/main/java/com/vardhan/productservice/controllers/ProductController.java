package com.vardhan.productservice.controllers;

import com.vardhan.productservice.dtos.CreateFakeStoreProductDto;
import com.vardhan.productservice.dtos.FakeStoreProductDto;
import com.vardhan.productservice.dtos.ProductResponseDto;
import com.vardhan.productservice.exceptions.ProductNotFoundException;
import com.vardhan.productservice.models.Product;
import com.vardhan.productservice.services.FakeStoreProductService;
import com.vardhan.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

//    private final RestTemplate restTemplate;
    FakeStoreProductService fakeStoreProductService;

    ProductService productService;

   public ProductController(@Qualifier("productDbService") ProductService productService) {
      this.productService = productService;
//       this.restTemplate = restTemplate;
   }
    // Less Used
    // @RequestMapping(value = "/products/id", method = RequestMethod.GET)/
   @GetMapping("/products/{id}")
    public ResponseEntity <ProductResponseDto> getProductById(@PathVariable long id) throws ProductNotFoundException {

       ResponseEntity responseEntity = new ResponseEntity<>(ProductResponseDto.from(productService.getProductById(id)), HttpStatus.OK);
      //Jackson : serialiation library
       return responseEntity;

   }

   @GetMapping("/products")
   public List<ProductResponseDto> getAllProducts() {

       List<ProductResponseDto> productResponseDos = new ArrayList<>();

       List<Product> products = productService.getAllProducts();

       for (Product product : products) {
           productResponseDos.add(ProductResponseDto.from(product));
       }
       return productResponseDos;
   }

   @PostMapping("/products")
   public ProductResponseDto createProduct(@RequestBody CreateFakeStoreProductDto   createFakeStoreProductDto ) throws ProductNotFoundException {

       Product product = productService.createProduct(
               createFakeStoreProductDto.getName(),
               createFakeStoreProductDto.getPrice(),
               createFakeStoreProductDto.getDescription(),
               createFakeStoreProductDto.getImageUrl(),
               createFakeStoreProductDto.getCategory()
       );

       return  ProductResponseDto.from(product);
   }
}
