package com.vardhan.productservice.services;

import com.vardhan.productservice.dtos.FakeStoreProductDto;
import com.vardhan.productservice.dtos.FakeStoreRequestDto;
import com.vardhan.productservice.exceptions.ProductNotFoundException;
import com.vardhan.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    RestTemplate restTemplate;

    // Insert rest template using Dependency Injection DI
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getProductById(long id) throws ProductNotFoundException {

        FakeStoreProductDto fakeStoreProductDto =  restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        if (fakeStoreProductDto == null) {
            throw  new ProductNotFoundException("Product for id " + id + "not found");
        }

        return fakeStoreProductDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();
       FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);


       for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
           products.add(fakeStoreProductDto.toProduct());
       }
       return products;
    }

    @Override
    public Product createProduct(String name, double price, String description, String imageUrl, String category)  {
        FakeStoreRequestDto fakeStoreRequestDto = new FakeStoreRequestDto();
        fakeStoreRequestDto.setTitle(name);
        fakeStoreRequestDto.setPrice(price);
        fakeStoreRequestDto.setDescription(description);
        fakeStoreRequestDto.setImage(imageUrl);
        fakeStoreRequestDto.setCategory(category);

        FakeStoreProductDto fakeStoreProductDto = restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreRequestDto, FakeStoreProductDto.class);

        return fakeStoreProductDto.toProduct();
    }


}
