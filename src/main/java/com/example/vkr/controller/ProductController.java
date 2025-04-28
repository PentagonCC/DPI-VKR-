package com.example.vkr.controller;

import com.example.vkr.model.Category;
import com.example.vkr.model.Product;
import com.example.vkr.model.ProductAttribute;
import com.example.vkr.model.User;
import com.example.vkr.service.CategoryService;
import com.example.vkr.service.ProductAttributeService;
import com.example.vkr.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
