package com.ampp8800.pizzeria;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;

public class OrderQueueWrapper {

    public static OrderQueueWrapper orderQueueWrapper;
    private Deque<Order> queueOrder = new LinkedList<>();
    private ArrayList<Integer> queueList = new ArrayList<>();
    final static int THE_NUMBER_OF_ORDERS = 11;

    private OrderQueueWrapper() {
    }

    public static OrderQueueWrapper getInstance() {
        if (orderQueueWrapper == null) {
            orderQueueWrapper = new OrderQueueWrapper();
        }
        return orderQueueWrapper;
    }

    public void printTotalOrders() {
        System.out.println(currentDate() + "the pizzeria will fulfill " + THE_NUMBER_OF_ORDERS + " orders");
    }

    public void addNewOrder(Order order) {
        queueOrder.add(order);
        queueList.add(order.getOrderNumber());
        printOrder(order);
    }


    public void printOrder(Order order) {
        System.out.print(currentDate() + "Order #" + order.getOrderNumber() + " ");
        System.out.println(order.getFood() + " " + order.getDate());
        System.out.println(currentDate() + "Orders in the queue " + queueList);
    }

    public String currentDate() {
        Date date = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("[dd/MM/yyyy HH:mm:ss] ");
        String strDate = DATE_FORMAT.format(date);
        return strDate;
    }

}
