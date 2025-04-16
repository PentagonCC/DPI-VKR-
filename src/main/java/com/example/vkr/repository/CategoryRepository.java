package com.example.vkr.repository;

import com.example.vkr.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository {

    @Query(value = "SELECT name FROM categories", nativeQuery = true)
    List<String> getAllCategories();
}
