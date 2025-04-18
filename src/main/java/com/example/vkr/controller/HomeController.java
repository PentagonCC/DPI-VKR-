package com.example.vkr.controller;

import com.example.vkr.model.Category;
import com.example.vkr.service.CategoryService;
import org.hibernate.annotations.AccessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public String mainPage(Model model){
        List<String> categoryList = categoryService.getAllCategories();
        model.addAttribute("categories", categoryList);
        return "main";
    }
}
