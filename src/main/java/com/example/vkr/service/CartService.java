package com.example.vkr.service;

import com.example.vkr.model.Cart;
import com.example.vkr.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    public List<Cart> getCartByUserId(Long userId) {
        return cartRepository.findCartByUserId(userId);
    }

    public void updateQuantity(int quantity, Long userId, Long productId) {
        cartRepository.updateQuantity(quantity, productId, userId);
    }

    public void deleteProduct(Long userId, Long productId) {
        cartRepository.deleteByUserIdAndProductId(userId, productId);
    }

    public void clearCart(Long userId) {
        cartRepository.clearCartByUserId(userId);
    }

    public void insertValuesInCart(Long userId, Long productId, int quantity) {
        cartRepository.insertValuesInCart(userId, productId, quantity);
    }
}
