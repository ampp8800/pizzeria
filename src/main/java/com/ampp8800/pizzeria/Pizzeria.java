package com.ampp8800.pizzeria;

public class Pizzeria {

    public static void main(String[] args) {
        OrderQueueWrapper orderQueueWrapper = OrderQueueWrapper.getInstance();
        orderQueueWrapper.printTotalOrders();
        OrderGenerator orderGenerator = new OrderGenerator();
        orderGenerator.start();
//        OrderGenerator orderGenerator1 = new OrderGenerator();
//        orderGenerator1.start();
//        OrderGenerator orderGenerator2 = new OrderGenerator();
//        orderGenerator2.start();

    }
}
