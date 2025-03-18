package com.vardhan.productservice.Repositories;

import com.vardhan.productservice.models.Category;
import com.vardhan.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

// First Argument is the table name in db
// Second argument referes to primary key
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(long aLong);
    Product save(Product product);
    List<Product> findByCategory(Category category);
    // Declarative JPA query
   List<Product> findByCategory_Name(String categoryName);
   //HQL
    @Query("select p from Product p where p.category.name = :categoryName")
    List<Product> findByCategoryName(@Param("categoryName") String categoryName);

    // NATIVE Query
    @Query(value = CustomQuery.GET_PRODUCTS_FROM_CATEGORY_NAME, nativeQuery = true )
    List<Product> getProductsByCategoryName(@Param("categoryName") String categoryName);
}
