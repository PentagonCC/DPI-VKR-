package com.example.vkr.service;

import com.example.vkr.model.Product;
import com.example.vkr.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProductByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public List<Product> getProductByAscPrice(Long categoryId) {
        return productRepository.findProductsByPriceAsc(categoryId);
    }

    public List<Product> getProductByDescPrice(Long categoryId) {
        return productRepository.findProductsByPriceDesc(categoryId);
    }

    public List<Product> getSearchedProduct(String name) {
        return productRepository.searchByName(name);
    }

    public List<Product> getSearchedProductPriceAsc(String name){
        return productRepository.searchByNamePriceAsc(name);
    }

    public List<Product> getSearchedProductPriceDesc(String name){
        return productRepository.searchByNamePriceDesc(name);
    }

    public List<Product> getRandomProduct() {
        return productRepository.findRandProduct();
    }

    public List<String> getAllManufacturers(Long categoryId) {
        return productRepository.findAllManufacturers(categoryId);
    }

    public List<Product> getByManufactureName(Long categoryId, String name) {
        return productRepository.findByManufactureName(name, categoryId);
    }

    public List<Product> getByManufactureNamePriceAsc(Long categoryId, String name) {
        return productRepository.findByManufactureNamePriceAsc(name, categoryId);
    }

    public List<Product> getByManufactureNamePriceDesc(Long categoryId, String name) {
        return productRepository.findByManufactureNamePriceDesc(name, categoryId);
    }

    public List<Product> getProductByManufacture(String manufacture) {
        return productRepository.findProductByManufacture(manufacture);
    }

    public List<String> getManufactures() {
        return productRepository.findManufactures();
    }

    public List<Product> getProductByManufacturePriceAsc(String manufacture) {
        return productRepository.findProductByManufacturePriceAsc(manufacture);
    }

    public List<Product> getProductByManufacturePriceDesc(String manufacture) {
        return productRepository.findProductByManufacturePriceDesc(manufacture);
    }

    public Product getProductById(Long productId){
        return productRepository.findProductById(productId);
    }

    public void updateStockQuantity(int quantity, Long id) {
        productRepository.updateStockQuantity(quantity, id);
    }
}
