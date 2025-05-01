package com.example.vkr.service;

import com.example.vkr.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public void insertPayment(Long orderId, String paymentMethod, String paymentStatus) {
        paymentRepository.insertPayment(orderId, paymentMethod, paymentStatus);
    }

}
