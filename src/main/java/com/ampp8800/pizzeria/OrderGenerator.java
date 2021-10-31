package com.ampp8800.pizzeria;

import java.util.concurrent.atomic.AtomicInteger;

public class OrderGenerator extends Thread {

    private static AtomicInteger ordersInProcessOfGeneration = new AtomicInteger(0);

    @Override
    public void run() {
        ProgramsMethods.addedOrderGenerator();
        OrderQueueWrapper orderQueueWrapper = OrderQueueWrapper.getInstance();
        while (ordersInProcessOfGeneration.getAndIncrement() < OrderQueueWrapper.THE_NUMBER_OF_ORDERS) {
            try {
                orderQueueWrapper.addNewOrder(generationOfOrders());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ProgramsMethods.generationOfOrdersCompleted();
    }

    private Order generationOfOrders() throws InterruptedException {

        int rouletteTime = ((int) (Math.random() * 7) + 3);
        Order order;
        Thread.sleep(rouletteTime * 1000);
        int rouletteFood = (int) (Math.random() * 3);

        switch (rouletteFood) {
            case 0:
                order = new Order(ordersInProcessOfGeneration.get(), Food.Pizza.PEPPERONI);
                break;
            case 1:
                order = new Order(ordersInProcessOfGeneration.get(), Food.Pizza.HAM_AND_MUSHROOMS);
                break;
            default:
                order = new Order(ordersInProcessOfGeneration.get(), Food.Pizza.MARGARITA);
                break;

        }
        return order;
    }
}
