package com.ampp8800.pizzeria;

public class Pizzeria {

    public static void main(String[] args) {
        OrderQueueWrapper orderQueueWrapper = OrderQueueWrapper.getInstance();
        orderQueueWrapper.printTotalOrders();
        OrderGenerator orderGenerator = new OrderGenerator();
        orderGenerator.start();

    }
}
