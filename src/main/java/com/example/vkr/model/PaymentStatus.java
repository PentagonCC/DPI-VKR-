package com.example.vkr.model;

public enum PaymentStatus {
    WAITING("Ожидает оплаты"),
    PAID("Оплачено");

    private final String title;

    PaymentStatus(String title) {
        this.title = title;
    }

    public String getTitle(){
        return title;
    }
}
