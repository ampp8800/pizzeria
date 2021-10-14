package main.java.com.ampp8800.pizzeria;

import java.util.Date;

public class Order {
    private int orderNumber;
    private Food.Pizza food;
    private Date date;

    public Order(int orderNumber, Food.Pizza food) {
        this.orderNumber = orderNumber;
        this.food = food;
        this.date = new Date();
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
