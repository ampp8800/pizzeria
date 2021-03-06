package com.ampp8800.pizzeria;

import java.util.concurrent.atomic.AtomicInteger;

public class OrderGenerator extends Thread {

    private static AtomicInteger ordersInProcessOfGeneration = new AtomicInteger(0);

    @Override
    public void run() {
        Utils.incrementNumberOfOrderGenerators();
        OrderQueueWrapper orderQueueWrapper = OrderQueueWrapper.getInstance();
        while (ordersInProcessOfGeneration.getAndIncrement() < Utils.THE_NUMBER_OF_ORDERS) {
            try {
                orderQueueWrapper.addNewOrder(generationOfOrders());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Utils.decrementNumberOfOrderGenerators();
    }

    private Order generationOfOrders() throws InterruptedException {

        int rouletteTime = ((int) (Math.random() * 7) + 3000);
        Order order;
        Thread.sleep(rouletteTime);
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
