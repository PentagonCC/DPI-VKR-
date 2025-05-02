package com.example.vkr.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Digits(integer = 8, fraction = 2)
    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull
    private Double totalPrice;

    @Column(nullable = false)
    @NotNull
    private String status;

    @Column(name = "created_at")
    @NotNull
    private LocalDateTime createdAt;

    public Order() {
    }

    public Order(Long id, Double totalPrice, String status) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Order(User user, Double totalPrice, String status, LocalDateTime createdAt) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(user, order.user) && Objects.equals(totalPrice,
                order.totalPrice) && Objects.equals(status, order.status) && Objects.equals(createdAt, order.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, totalPrice, status, createdAt);
    }
}
