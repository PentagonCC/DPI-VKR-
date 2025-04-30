package com.example.vkr.controller;

import com.example.vkr.model.Category;
import com.example.vkr.model.User;
import com.example.vkr.service.CartService;
import com.example.vkr.service.CategoryService;
import com.example.vkr.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    CartService cartService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    OrderService orderService;

    @GetMapping("/registration-order")
    public String getRegistrationOrderPage(@AuthenticationPrincipal User user, Model model){
        List<Category> categoryList = categoryService.getAllCategories();
        model.addAttribute("categories", categoryList);
        model.addAttribute("user", user);
        return "registration-order";
    }

    @GetMapping("/orders")
    public String getOrdersPage(@AuthenticationPrincipal User user, Model model){
        List<Category> categoryList = categoryService.getAllCategories();
        model.addAttribute("categories", categoryList);
        model.addAttribute("user", user);
        return "orders";
    }
}
