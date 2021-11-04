package com.ampp8800.pizzeria;

import java.util.Deque;
import java.util.LinkedList;

public class OrderQueueWrapper {

    private static OrderQueueWrapper orderQueueWrapper;
    private Deque<Order> queueOrder = new LinkedList<>();
    final static int THE_NUMBER_OF_ORDERS = 11;


    private OrderQueueWrapper() {
    }

    public static synchronized OrderQueueWrapper getInstance() {
        if (orderQueueWrapper == null) {
            orderQueueWrapper = new OrderQueueWrapper();
        }
        return orderQueueWrapper;
    }

    public Deque<Order> getQueueOrder() {
        return queueOrder;
    }

    public void addNewOrder(Order order) {
        queueOrder.add(order);
        printOrder(order);
    }


    public void printOrder(Order order) {
        System.out.println(Utils.currentDate() + "Add order #" + order.getOrderNumber() + " " + order.getFood() + " " + order.getDate());
        System.out.println(Utils.currentDate() + "Orders in the queue " + queueOrder);
    }

}
