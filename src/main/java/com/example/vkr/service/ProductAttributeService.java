package com.example.vkr.service;

import com.example.vkr.model.ProductAttribute;
import com.example.vkr.repository.ProductAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductAttributeService {

    @Autowired
    ProductAttributeRepository productAttributeRepository;

    public List<ProductAttribute> getByProductId(Long productId) {
        return productAttributeRepository.findByProductId(productId);
    }

    public List<ProductAttribute> getByProductIdValue(Long productId, String value) {
        return productAttributeRepository.findByAttributeIdAndValue(productId, value);
    }

    public Map<String, String> getAttributesByProductId(Long productId) {
        List<Object[]> results = productAttributeRepository.findAttributesByProductId(productId);
        Map<String, String> attributesMap = new HashMap<>();

        for (Object[] result : results) {
            String attributeName = (String) result[0];
            String attributeValue = (String) result[1];

            if (attributesMap.containsKey(attributeName)) {
                String existingValue = attributesMap.get(attributeName);
                attributesMap.put(attributeName, existingValue + ", " + attributeValue);
            } else {
                attributesMap.put(attributeName, attributeValue);
            }
        }

        return attributesMap;
    }
}
