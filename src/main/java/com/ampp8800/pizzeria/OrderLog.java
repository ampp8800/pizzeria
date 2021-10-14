package main.java.com.ampp8800.pizzeria;

import java.text.SimpleDateFormat;
import java.util.*;

public class OrderLog {

    private static Deque<Order> queueOrder = new LinkedList<>();
    private static ArrayList<Integer> queeuList = new ArrayList<>();

    public OrderLog() {
        OrderGenerator orderGenerator = new OrderGenerator();
        printDate();
        System.out.println("the pizzeria will fulfill "+ orderGenerator.THE_NUMBER_OF_ORDERS +" orders");
        orderGenerator.start();

    }

    public static void addNewOrder (Order order) {
        queueOrder.offer(order);
        queeuList.add(order.getOrderNumber());
        printOrder(order.getOrderNumber());
    }


    public static void printOrder(int number) {
        printDate();
        System.out.print("Order #" + (number) + " ");
        System.out.println(queueOrder.getLast().getFood() + " " + queueOrder.getLast().getDate());
        printDate();
        System.out.println("Orders in the queue " + queeuList);

    }

    public static void printDate() {
        Date date = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("[dd/MM/yyyy hh:mm:ss] ");
        String strDate = DATE_FORMAT.format(date);
        System.out.print(strDate);
    }

}
