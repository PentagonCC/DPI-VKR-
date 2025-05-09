package com.example.vkr.controller;

import com.example.vkr.model.Category;
import com.example.vkr.model.Product;
import com.example.vkr.model.User;
import com.example.vkr.service.CategoryService;
import com.example.vkr.service.ProductService;
import org.hibernate.annotations.AccessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static java.lang.Long.parseLong;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String mainPage(Model model){
        List<Category> categoryList = categoryService.getAllCategories();
        List<Product> productList = productService.getRandomProduct();
        model.addAttribute("products", productList);
        model.addAttribute("categories", categoryList);
        return "main";
    }

    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal User user, Model model){
        List<Category> categoryList = categoryService.getAllCategories();
        model.addAttribute("categories", categoryList);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/policy-confidence")
    public String getPolicyConfidence(){
        return "policy-confidence";
    }

    @GetMapping("/about-us")
    public String getAboutUs(){
        return "about-us";
    }

    @GetMapping("/selling-rule")
    public String getSellingRule(){
        return "selling-rule";
    }

}
