package com.example.vkr.service;

import com.example.vkr.model.Order;
import com.example.vkr.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getByUserId(Long userId){
        return orderRepository.findByUserId(userId);
    }
}
