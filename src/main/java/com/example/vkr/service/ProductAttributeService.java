package com.example.vkr.service;

import com.example.vkr.model.ProductAttribute;
import com.example.vkr.repository.ProductAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductAttributeService {

    @Autowired
    ProductAttributeRepository productAttributeRepository;

    public List<ProductAttribute> getByProductId(Long productId){
        return productAttributeRepository.findByProductId(productId);
    }

    public List<ProductAttribute> getByProductIdValue(Long productId, String value){
        return productAttributeRepository.findByAttributeIdAndValue(productId, value);
    }
}
