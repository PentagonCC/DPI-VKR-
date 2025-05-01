package com.example.vkr.service;

import com.example.vkr.model.Order;
import com.example.vkr.model.OrderStatus;
import com.example.vkr.model.User;
import com.example.vkr.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Transactional
    public Long addNewOrder(User user, double totalPrice, String status, LocalDateTime createdAt) {
        Order order = new Order(user, totalPrice, status, createdAt);
        orderRepository.save(order);
        return order.getId();
    }
}
