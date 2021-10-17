package com.ampp8800.pizzeria;

import java.util.Date;

public class Order {
    private int orderNumber;
    private Food.Pizza food;
    private Date date;

    public Order(Food.Pizza food) {
        this.food = food;
        this.date = new Date();
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Food.Pizza getFood() {
        return food;
    }

    public Date getDate() {
        return date;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
}
