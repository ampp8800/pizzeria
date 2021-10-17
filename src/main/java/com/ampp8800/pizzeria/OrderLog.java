package com.ampp8800.pizzeria;

import java.text.SimpleDateFormat;
import java.util.*;

public class OrderLog {

    private static Deque<Order> queueOrder = new LinkedList<>();
    private static ArrayList<Integer> queueList = new ArrayList<>();
    private static int currentOrderNumber = 0;

    public void addNewOrderLog() {
        OrderGenerator orderGenerator = new OrderGenerator();
        System.out.println(printDate() + "the pizzeria will fulfill "+ OrderGenerator.THE_NUMBER_OF_ORDERS +" orders");
        orderGenerator.start();

    }

    public static void addNewOrder (Order order) {
        currentOrderNumber++;
        order.setOrderNumber(currentOrderNumber);
        queueOrder.offer(order);
        queueList.add(currentOrderNumber);
        printOrder(currentOrderNumber);
    }


    public static void printOrder(int number) {
        System.out.print(printDate() + "Order #" + (number) + " ");
        System.out.println(queueOrder.getLast().getFood() + " " + queueOrder.getLast().getDate());
        System.out.println(printDate() + "Orders in the queue " + queueList);

    }

    public static String printDate() {
        Date date = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("[dd/MM/yyyy hh:mm:ss] ");
        String strDate = DATE_FORMAT.format(date);
        return strDate;
    }

}
