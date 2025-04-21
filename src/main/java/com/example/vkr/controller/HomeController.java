package com.example.vkr.controller;

import com.example.vkr.model.Category;
import com.example.vkr.model.Product;
import com.example.vkr.service.CategoryService;
import com.example.vkr.service.ProductService;
import org.hibernate.annotations.AccessType;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getProfile(Model model){
        List<Category> categoryList = categoryService.getAllCategories();
        model.addAttribute("categories", categoryList);
        return "profile";
    }

    @GetMapping("/catalog/{categoryId}")
    public String getCatalog(@PathVariable Long categoryId,
                             @RequestParam(required = false) String sort, Model model) {
        List<Category> categoryList = categoryService.getAllCategories();
        List<String> productAttributes = productService.getManufacturers(categoryId);
        List<Product> productList;
        if ("desc".equals(sort)) {
            productList = productService.getProductByDescPrice(categoryId);
        } else if ("asc".equals(sort)) {
            productList = productService.getProductByAscPrice(categoryId);
        } else {
            productList = productService.getAllProductByCategory(categoryId);
        }
        model.addAttribute("categories", categoryList);
        model.addAttribute("products", productList);
        model.addAttribute("attributes", productAttributes);
        return "catalog";
    }
}
