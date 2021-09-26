package main.java.com.ampp8800.pizzeria;

import java.util.Date;

public class Order {
    private Food.Pizza food;
    private Date date;

    public Order(Food.Pizza food) {
        this.food = food;
        this.date = new Date();
    }

    public Food.Pizza getFood() {
        return food;
    }

    public Date getDate() {
        return date;
    }
}
