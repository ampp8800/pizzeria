package main.java.com.ampp8800.pizzeria;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OrderLog extends Thread {
    final int THE_NUMBER_OF_ORDERS = 11;
    private int orderNumber = 0;
    private HashMap<Integer, Order> mapOrder = new HashMap<>();

    @Override
    public void run() {
        for (int i = 0; i < THE_NUMBER_OF_ORDERS; i++) {
            try {
                addNewOrder();
                printOrder(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public HashMap<Integer, Order> getMapOrder() {
        return mapOrder;
    }

    public void addNewOrder() throws InterruptedException {
        this.orderNumber++;
        OrderGenerator orderGenerator = new OrderGenerator();
        orderGenerator.generationOfOrders();
        mapOrder.put(orderNumber, orderGenerator.getOrder());
    }

    public void printOrder(int number) {
        Date date = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("[dd/MM/yyyy hh:mm:ss] ");
        String strDate = DATE_FORMAT.format(date);
        System.out.print(strDate + "Order #" + (number + 1) + " ");
        System.out.println(mapOrder.get(number + 1).getFood() + " " + mapOrder.get(number + 1).getDate());
        System.out.print(strDate + "Orders in the queue: ");
        for (Map.Entry<Integer, Order> entry : mapOrder.entrySet()) {
            System.out.print("#" + entry.getKey() + ", ");
        }
        System.out.println();
    }

}
