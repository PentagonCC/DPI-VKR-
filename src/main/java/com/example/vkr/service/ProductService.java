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

    public List<Product> getProductByAscPrice(Long categoryId){
        return productRepository.findProductsByPriceAsc(categoryId);
    }

    public List<Product> getProductByDescPrice(Long categoryId){
        return productRepository.findProductsByPriceDesc(categoryId);
    }

    public List<Product> getSearchedProduct(String name){
        return productRepository.searchByName(name);
    }

    public List<Product> getRandomProduct(){
        return productRepository.findRandProduct();
    }

    public List<String> getManufacturers(Long categoryId){
        return productRepository.findAllManufacturers(categoryId);
    }
}
