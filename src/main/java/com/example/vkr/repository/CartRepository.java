package com.example.vkr.repository;

import com.example.vkr.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "SELECT * FROM cart WHERE user_id = :userId", nativeQuery = true)
    List<Cart> findCartByUserId(@Param("userId") Long userID);

    @Modifying
    @Transactional
    @Query(value = "UPDATE cart SET quantity = :quantity WHERE product_id = :productId AND user_id = :userId",
            nativeQuery = true)
    void updateQuantity(@Param("quantity") int quantity, @Param("productId") Long productId, @Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cart WHERE user_id = :userId AND product_id = :productId", nativeQuery = true)
    void deleteByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cart WHERE user_id = :userId", nativeQuery = true)
    void clearCartByUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cart (user_id, product_id, quantity) VALUES (:userId, :productId, :quantity)",
            nativeQuery = true)
    void insertValuesInCart(@Param("userId") Long userId, @Param("productId") Long productId,
                            @Param("quantity") int quantity);
}
