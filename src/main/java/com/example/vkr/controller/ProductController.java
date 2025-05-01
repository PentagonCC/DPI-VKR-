package com.example.vkr.controller;

import com.example.vkr.model.Cart;
import com.example.vkr.model.Category;
import com.example.vkr.model.Product;
import com.example.vkr.model.User;
import com.example.vkr.service.CartService;
import com.example.vkr.service.CategoryService;
import com.example.vkr.service.ProductAttributeService;
import com.example.vkr.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductAttributeService productAttributeService;
    @Autowired
    CartService cartService;

    @GetMapping("/product/{productId}")
    public String getProductCard(@AuthenticationPrincipal User user, @PathVariable Long productId, Model model) {
        List<Category> categoryList = categoryService.getAllCategories();
        Product product = productService.getProductById(productId);
        Map<String, String> productAttributeList = productAttributeService.getAttributesByProductId(productId);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryList);
        model.addAttribute("productAttributes", productAttributeList);
        model.addAttribute("user", user);
        return "product_card";
    }

    @PostMapping("/product/{productId}")
    public String addProductInCart(@AuthenticationPrincipal User user, @PathVariable Long productId, Model model) {
        List<Category> categoryList = categoryService.getAllCategories();
        Product product = productService.getProductById(productId);
        Map<String, String> productAttributeList = productAttributeService.getAttributesByProductId(productId);
        List<Cart> cartUser = cartService.getCartByUserId(user.getId());
        boolean addFlag = true;
        for (Cart cart : cartUser) {
            List<Long> products = new ArrayList<>();
            products.add(cart.getProduct().getId());
            for (Long id : products) {
                if (cart.getProduct().getId().equals(productId)) {
                    addFlag = false;
                }
            }
        }
        if (addFlag){
            cartService.insertValuesInCart(user.getId(), productId, 1);
        }
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryList);
        model.addAttribute("productAttributes", productAttributeList);
        model.addAttribute("user", user);
        return "product_card";
    }
}
