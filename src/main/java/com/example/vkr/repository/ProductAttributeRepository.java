package com.example.vkr.repository;

import com.example.vkr.model.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Long> {

    @Query(value = "SELECT * FROM product_attributes WHERE product_id = :productId", nativeQuery = true)
    List<ProductAttribute> findByProductId(@Param("productId") Long productId);

    @Query(value = " SELECT * FROM product_attributes WHERE attribute_id = :attributeId AND value = :value ",
            nativeQuery = true)
    List<ProductAttribute> findByAttributeIdAndValue(@Param("attributeId") Long attributeId,
                                                     @Param("value") String value);

    @Query(value = "SELECT a.name, pa.value FROM product_attributes pa JOIN attributes a ON " +
            "pa.attribute_id = a.id WHERE pa.product_id = :productId", nativeQuery = true)
    List<Object[]> findAttributesByProductId(@Param("productId") Long productId);

}
