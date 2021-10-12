package main.java.com.ampp8800.pizzeria;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OrderLog {

    private static HashMap<Integer, Order> mapOrder = new HashMap<>();

    public OrderLog() {
        OrderGenerator orderGenerator = new OrderGenerator();
        System.out.println("the pizzeria will fulfill "+ orderGenerator.THE_NUMBER_OF_ORDERS +" orders");
        orderGenerator.start();

    }

    public HashMap<Integer, Order> getMapOrder() {
        return mapOrder;
    }

    public static void addNewOrder (int orderNumber, Order order) {
        mapOrder.put(orderNumber, order);
        printOrder(orderNumber);
    }


    public static void printOrder(int number) {
        Date date = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("[dd/MM/yyyy hh:mm:ss] ");
        String strDate = DATE_FORMAT.format(date);
        System.out.print(strDate + "Order #" + (number) + " ");
        System.out.println(mapOrder.get(number).getFood() + " " + mapOrder.get(number).getDate());
        System.out.print(strDate + "Orders in the queue: ");
        for (Map.Entry<Integer, Order> entry : mapOrder.entrySet()) {
            System.out.print("#" + entry.getKey() + ", ");
        }
        System.out.println();
    }

}
