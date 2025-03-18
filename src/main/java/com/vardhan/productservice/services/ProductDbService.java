package com.vardhan.productservice.services;

import com.vardhan.productservice.Repositories.CategoryRepository;
import com.vardhan.productservice.Repositories.ProductRepository;
import com.vardhan.productservice.exceptions.ProductNotFoundException;
import com.vardhan.productservice.models.Category;
import com.vardhan.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service("productDbService")
public class ProductDbService implements ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ProductDbService(ProductRepository repository, CategoryRepository categoryRepository) {
        this.productRepository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {

        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw  new ProductNotFoundException("Product with id " + id + "not found");
        }
        return product.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();

    }

    @Override
    public Product createProduct(String name, double price, String description, String imageUrl, String category) throws ProductNotFoundException {
       Product product = new Product();
       product.setName(name);
       product.setPrice(price);
       product.setDescription(description);
       product.setImageUrl(imageUrl);

        Category categoryObj = getCategory(category);
        product.setCategory(categoryObj);
        // Save in db
       return productRepository.save(product);
    }

    private Category getCategory(String categoryName) {
       Optional <Category> optionalCategory =  categoryRepository.findByName(categoryName);

       if(optionalCategory.isPresent()) {
           return optionalCategory.get();
       } else {
           Category category = new Category();
           category.setName(categoryName);
            return  categoryRepository.save(category);
       }
    }
}
