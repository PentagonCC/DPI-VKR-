package com.example.vkr.repository;

import com.example.vkr.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM orders WHERE user_id = :userId", nativeQuery = true)
    List<Order> findByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT o.total_price, o.id, o.status, p.name FROM orders o " +
            "JOIN order_items oi ON o.id = oi.order_id " +
            "JOIN products p ON p.id = oi.product_id " +
            "WHERE o.user_id = :userId", nativeQuery = true)
    List<Object[]> findOrderWithProduct(@Param("userId") Long userId);

}
