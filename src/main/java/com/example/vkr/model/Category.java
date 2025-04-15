package com.example.vkr.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "cashback")
    private int cashBack;

    public Category(String name, int cashBack) {
        this.name = name;
        this.cashBack = cashBack;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCashBack() {
        return cashBack;
    }

    public void setCashBack(int cashBack) {
        this.cashBack = cashBack;
    }
}
