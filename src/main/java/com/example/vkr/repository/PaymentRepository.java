package com.example.vkr.repository;

import com.example.vkr.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO payments (order_id, payment_method, payment_status) " +
            "VALUES (:orderId, :paymentMethod, :paymentStatus)", nativeQuery = true)
    void insertPayment(@Param("orderId") Long orderId, @Param("paymentMethod") String paymentMethod,
                       @Param("paymentStatus") String paymentStatus);
}
