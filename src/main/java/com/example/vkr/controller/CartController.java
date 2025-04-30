package com.example.vkr.controller;

import com.example.vkr.model.Category;
import com.example.vkr.model.User;
import com.example.vkr.service.CartService;
import com.example.vkr.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/cart")
    public String getCartPage(@AuthenticationPrincipal User user, Model model) {
        List<Category> categoryList = categoryService.getAllCategories();
        model.addAttribute("categories", categoryList);
        model.addAttribute(user);
        return "cart";
    }


}
