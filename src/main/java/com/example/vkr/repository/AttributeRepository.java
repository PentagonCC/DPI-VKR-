package com.example.vkr.repository;

import com.example.vkr.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {

    @Query(value = "SELECT * FROM attributes WHERE name = :name", nativeQuery = true)
    Attribute findByName(@Param("name") String name);
}
