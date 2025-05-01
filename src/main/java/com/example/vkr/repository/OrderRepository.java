package com.example.vkr.repository;

import com.example.vkr.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM orders WHERE user_id = :userId", nativeQuery = true)
    List<Order> findByUserId(@Param("userId") Long userId);

//    @Transactional
//    @Modifying
//    @Query(value = "INSERT INTO orders (user_id, total_price, status, created_at) " +
//            "VALUES (:userId, :totalPrice, :status, :createdAt) RETURNING *", nativeQuery = true)
//    void insertNewOrder(@Param("userId") Long userId, @Param("totalPrice") double totalPrice,
//                         @Param("status") String status, @Param("createdAt") LocalDateTime createdAt);
}
