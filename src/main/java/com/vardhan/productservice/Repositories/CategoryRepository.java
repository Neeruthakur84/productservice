package com.vardhan.productservice.Repositories;

import com.vardhan.productservice.models.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Used to force to apply custom modes
    @EntityGraph(attributePaths = "products")
    Optional<Category> findByName(String name);
    Category save(Category category);


}
