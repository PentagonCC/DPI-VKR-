package com.example.vkr.service;

import com.example.vkr.model.OrderItem;
import com.example.vkr.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    public List<OrderItem> getByOrderId(Long orderId){
        return orderItemRepository.findByOrderId(orderId);
    }
}
