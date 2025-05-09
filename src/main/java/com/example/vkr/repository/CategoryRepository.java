package com.example.vkr.repository;

import com.example.vkr.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT name FROM categories", nativeQuery = true)
    List<String> getNameCategories();

    @Query(value = "SELECT * FROM categories", nativeQuery = true)
    List<Category> getAllCategories();
}
