package com.example.vkr.repository;

import com.example.vkr.model.Attribute;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeRepository {

    @Query(value = "SELECT * FROM attributes WHERE name = :name", nativeQuery = true)
    Attribute findByName(@Param("name") String name);
}
