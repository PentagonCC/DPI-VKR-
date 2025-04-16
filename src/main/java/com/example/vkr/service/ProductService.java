package com.example.vkr.service;

import com.example.vkr.model.Product;
import com.example.vkr.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProductByCategory(Long categoryId){
        return productRepository.findByCategoryId(categoryId);
    }

    public List<Product> getProductByAscPrice(){
        return productRepository.findAllOrderByPriceAsc();
    }

    public List<Product> getProductByDescPrice(){
        return productRepository.findAllOrderByPriceDesc();
    }

    public List<Product> getSearchedProduct(String name){
        return productRepository.searchByName(name);
    }
}
