package com.example.vkr.service;

import com.example.vkr.model.Attribute;
import com.example.vkr.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeService {

    @Autowired
    AttributeRepository attributeRepository;

    public Attribute getByName(String name){
        return attributeRepository.findByName(name);
    }
}
