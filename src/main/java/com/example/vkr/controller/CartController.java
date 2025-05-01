package com.example.vkr.controller;

import com.example.vkr.model.Cart;
import com.example.vkr.model.Category;
import com.example.vkr.model.Product;
import com.example.vkr.model.User;
import com.example.vkr.service.CartService;
import com.example.vkr.service.CategoryService;
import com.example.vkr.service.ProductService;
import com.example.vkr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/cart")
    public String getCartPage(@RequestParam(value = "useBonuses", required = false) String useBonuses,
                              @AuthenticationPrincipal User user, Model model) {
        List<Cart> cartList = cartService.getCartByUserId(user.getId());
        Map<Product, Integer> cartItems = new HashMap<>();
        int bonusBalance = user.getBonuses();
        double totalPrice = 0;
        for(Cart cart : cartList) {
            Long productId = cart.getProduct().getId();
            cartItems.put(productService.getProductById(productId), cart.getQuantity());
            totalPrice += productService.getProductById(productId).getPrice() * cart.getQuantity();
        }
        if(useBonuses != null && totalPrice > user.getBonuses()){
            totalPrice-= user.getBonuses();
            bonusBalance = 0;
        } else if (useBonuses != null && totalPrice < user.getBonuses()) {
            totalPrice = 1;
            bonusBalance = (int) (bonusBalance - totalPrice + 1);
        }
        List<Category> categoryList = categoryService.getAllCategories();
        model.addAttribute("bonusBalance", bonusBalance);
        model.addAttribute("useBonuses", useBonuses);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("categories", categoryList);
        model.addAttribute(user);
        return "cart";
    }

    @PostMapping("/cart/minusQuantity")
    public String minusQuantityProduct(@AuthenticationPrincipal User user, @RequestParam("productId") Long productId){
        List<Cart> cartList = cartService.getCartByUserId(user.getId());
        for (Cart cart : cartList){
            if(cart.getProduct().getId().equals(productId)){
                if(cart.getQuantity() != 1) {
                    cartService.updateQuantity(cart.getQuantity() - 1, user.getId(), productId);
                }
                else{
                    cartService.deleteProduct(user.getId(), productId);
                }
            }
        }
        return "redirect:/cart";
    }

    @PostMapping("/cart/plusQuantity")
    public String plusQuantityProduct(@AuthenticationPrincipal User user, @RequestParam("productId") Long productId){
        List<Cart> cartList = cartService.getCartByUserId(user.getId());
        for (Cart cart : cartList){
            if(cart.getProduct().getId().equals(productId)){
                if(cart.getQuantity() != productService.getProductById(productId).getStockQuantity()) {
                    cartService.updateQuantity(cart.getQuantity() + 1, user.getId(), productId);
                }
            }
        }
        return "redirect:/cart";
    }


}
