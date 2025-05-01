package com.example.vkr.repository;

import com.example.vkr.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Query(value = "SELECT * FROM products WHERE id = :productId", nativeQuery = true)
    Product findProductById(@Param("productId") Long productId);

    @Query(value = "SELECT DISTINCT pa.value AS manufacturer FROM product_attributes pa JOIN attributes a ON" +
            " pa.attribute_id = a.id JOIN products p ON pa.product_id = p.id WHERE a.name = 'Производитель' AND " +
            "p.category_id = :categoryId ", nativeQuery = true)
    List<String> findAllManufacturers(@Param("categoryId") Long categoryId);

    @Query(value = "SELECT DISTINCT pa.value AS manufacturer FROM product_attributes pa JOIN attributes a ON" +
            " pa.attribute_id = a.id JOIN products p ON pa.product_id = p.id WHERE a.name = 'Производитель'",
            nativeQuery = true)
    List<String> findManufactures();

    @Query(value = "SELECT pr.* FROM products pr JOIN product_attributes pa ON pr.id=pa.product_id " +
            "JOIN attributes atr ON pa.attribute_id=atr.id WHERE atr.name='Производитель' AND pa.value= :manufacture " +
            "AND pr.category_id= :categoryId", nativeQuery = true)
    List<Product> findByManufactureName(@Param("manufacture") String manufacture, @Param("categoryId") Long categoryId);

    @Query(value = "SELECT pr.* FROM products pr JOIN product_attributes pa ON pr.id=pa.product_id " +
            "JOIN attributes atr ON pa.attribute_id=atr.id WHERE atr.name='Производитель' AND pa.value= :manufacture " +
            "AND pr.category_id= :categoryId ORDER BY price ASC", nativeQuery = true)
    List<Product> findByManufactureNamePriceAsc(@Param("manufacture") String manufacture, @Param("categoryId") Long categoryId);

    @Query(value = "SELECT pr.* FROM products pr JOIN product_attributes pa ON pr.id=pa.product_id " +
            "JOIN attributes atr ON pa.attribute_id=atr.id WHERE atr.name='Производитель' AND pa.value= :manufacture " +
            "AND pr.category_id= :categoryId ORDER BY price DESC", nativeQuery = true)
    List<Product> findByManufactureNamePriceDesc(@Param("manufacture") String manufacture, @Param("categoryId") Long categoryId);

    @Query(value = "SELECT pr.* FROM products pr JOIN product_attributes pa ON pr.id=pa.product_id " +
            "JOIN attributes atr ON pa.attribute_id=atr.id WHERE atr.name='Производитель' AND pa.value= :manufacture",
            nativeQuery = true)
    List<Product> findProductByManufacture(@Param("manufacture") String manufacture);

    @Query(value = "SELECT pr.* FROM products pr JOIN product_attributes pa ON pr.id=pa.product_id " +
            "JOIN attributes atr ON pa.attribute_id=atr.id WHERE atr.name='Производитель' AND pa.value= :manufacture " +
            "ORDER BY price DESC", nativeQuery = true)
    List<Product> findProductByManufacturePriceDesc(@Param("manufacture") String manufacture);

    @Query(value = "SELECT pr.* FROM products pr JOIN product_attributes pa ON pr.id=pa.product_id " +
            "JOIN attributes atr ON pa.attribute_id=atr.id WHERE atr.name='Производитель' AND pa.value= :manufacture " +
            "ORDER BY price ASC", nativeQuery = true)
    List<Product> findProductByManufacturePriceAsc(@Param("manufacture") String manufacture);

    @Transactional
    @Modifying
    @Query(value = "UPDATE products SET stock_quantity = :quantity WHERE id = :id", nativeQuery = true)
    void updateStockQuantity(@Param("quantity") int quantity, @Param("id") Long id);
}
