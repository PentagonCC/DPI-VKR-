package com.example.vkr.repository;

import com.example.vkr.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM products WHERE LOWER(name) LIKE LOWER(CONCAT('%', :keyword, '%'))", nativeQuery = true)
    List<Product> searchByName(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM products WHERE category_id = :categoryId", nativeQuery = true)
    List<Product> findByCategoryId(@Param("categoryId") Long categoryId);

    @Query(value = "SELECT * FROM products WHERE category_id = :categoryId ORDER BY price ASC", nativeQuery = true)
    List<Product> findProductsByPriceAsc(@Param("categoryId") Long categoryId);

    @Query(value = "SELECT * FROM products WHERE category_id = :categoryId ORDER BY price DESC", nativeQuery = true)
    List<Product> findProductsByPriceDesc(@Param("categoryId") Long categoryId);

    @Query(value = "SELECT * FROM products ORDER BY RANDOM() LIMIT 4", nativeQuery = true)
    List<Product> findRandProduct();

    @Query(value = "SELECT DISTINCT pa.value AS manufacturer FROM product_attributes pa JOIN attributes a ON" +
            " pa.attribute_id = a.id JOIN products p ON pa.product_id = p.id WHERE a.name = 'Производитель' AND " +
            "p.category_id = :categoryId ", nativeQuery = true)
    List<String> findAllManufacturers(@Param("categoryId") Long categoryId);
}
