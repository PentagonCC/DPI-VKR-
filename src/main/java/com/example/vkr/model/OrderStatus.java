package com.example.vkr.model;

public enum OrderStatus {
    NEW("Новый"),
    DONE("Выполнен"),
    READY("Готов к получению"),
    ASSEMBLY("В сборке");

    private final String title;

    OrderStatus(String title) {
        this.title = title;
    }

    public String getTitle(){
        return title;
    }
}
