package com.ampp8800.pizzeria;

import java.util.concurrent.atomic.AtomicInteger;

public class OrderGenerator extends Thread {

    private static AtomicInteger orderQuantity = new AtomicInteger(0);
    private static AtomicInteger currentNumber = new AtomicInteger(0);

    @Override
    public void run() {
        OrderQueueWrapper orderQueueWrapper = OrderQueueWrapper.getInstance();
        while (orderQuantity.getAndIncrement() < orderQueueWrapper.THE_NUMBER_OF_ORDERS) {
            try {
                orderQueueWrapper.addNewOrder(generationOfOrders());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Order generationOfOrders() throws InterruptedException {

        int rouletteTime = ((int) (Math.random() * 7) + 3);
        Order order;
        Thread.sleep(rouletteTime * 1000);
        int rouletteFood = (int) (Math.random() * 3);
        synchronized (this) {
            switch (rouletteFood) {
                case 0:
                    order = new Order(currentNumber.incrementAndGet(), Food.Pizza.PEPPERONI);
                    break;
                case 1:
                    order = new Order(currentNumber.incrementAndGet(), Food.Pizza.HAM_AND_MUSHROOMS);
                    break;
                default:
                    order = new Order(currentNumber.incrementAndGet(), Food.Pizza.MARGARITA);
                    break;
            }
        }
        return order;
    }
}
