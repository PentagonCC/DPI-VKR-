package com.example.vkr.controller;

import com.example.vkr.model.Category;
import com.example.vkr.model.Product;
import com.example.vkr.model.User;
import com.example.vkr.service.CategoryService;
import com.example.vkr.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CatalogController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/catalog/{categoryId}/{manufactureName}")
    public String getCatalogByManufacture(@AuthenticationPrincipal User user, @PathVariable Long categoryId,
                                          @PathVariable String manufactureName, Model model,
                                          @RequestParam(required = false) String sort) {
        List<Category> categoryList = categoryService.getAllCategories();
        List<String> productAttributes = productService.getAllManufacturers(categoryId);
        List<Product> productList;
        if ("desc".equals(sort)) {
            productList = productService.getByManufactureNamePriceDesc(categoryId, manufactureName);
        } else if ("asc".equals(sort)) {
            productList = productService.getByManufactureNamePriceAsc(categoryId, manufactureName);
        } else {
            productList = productService.getByManufactureName(categoryId, manufactureName);
        }
        model.addAttribute("categories", categoryList);
        model.addAttribute("products", productList);
        model.addAttribute("attributes", productAttributes);
        model.addAttribute("user", user);
        return "catalog";
    }

    @GetMapping("/catalog/{categoryId}")
    public String getCatalog(@AuthenticationPrincipal User user, @PathVariable Long categoryId,
                             @RequestParam(required = false) String sort, Model model) {
        List<Category> categoryList = categoryService.getAllCategories();
        List<String> productAttributes = productService.getAllManufacturers(categoryId);
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
        model.addAttribute("category", categoryId);
        model.addAttribute("user", user);
        return "catalog";
    }

    @GetMapping("/catalog/brand/{popularManufacture}")
    public String getCatalogByPopularManufacture(@AuthenticationPrincipal User user, @PathVariable String popularManufacture,
                                                 @RequestParam(required = false) String sort, Model model) {
        List<Category> categoryList = categoryService.getAllCategories();
        List<String> productAttributes = productService.getManufactures();
        List<Product> productList;
        if ("desc".equals(sort)) {
            productList = productService.getProductByManufacturePriceDesc(popularManufacture);
        } else if ("asc".equals(sort)) {
            productList = productService.getProductByManufacturePriceAsc(popularManufacture);
        } else {
            productList = productService.getProductByManufacture(popularManufacture);
        }
        model.addAttribute("categories", categoryList);
        model.addAttribute("attributes", productAttributes);
        model.addAttribute("products", productList);
        model.addAttribute("popular", popularManufacture);
        model.addAttribute("user", user);
        return "catalog";
    }

    @GetMapping("/catalog")
    public String getCatalogWithSearchProducts(@AuthenticationPrincipal User user, @RequestParam String keyword,
                                               @RequestParam(required = false) String sort, Model model) {
        List<Category> categoryList = categoryService.getAllCategories();
        List<String> productAttributes = productService.getManufactures();
        List<Product> productList;
        if ("desc".equals(sort)) {
            productList = productService.getSearchedProductPriceDesc(keyword);
        } else if ("asc".equals(sort)) {
            productList = productService.getSearchedProductPriceAsc(keyword);
        } else {
            productList = productService.getSearchedProduct(keyword);
        }
        model.addAttribute("categories", categoryList);
        model.addAttribute("attributes", productAttributes);
        model.addAttribute("products", productList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("user", user);
        return "catalog";
    }

}
