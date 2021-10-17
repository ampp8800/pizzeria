package com.ampp8800.pizzeria;

public class OrderGenerator extends Thread{

    final static int THE_NUMBER_OF_ORDERS = 11;
    private static int orderQuantity = 0;



    @Override
    public void run() {
        while (orderQuantity < THE_NUMBER_OF_ORDERS) {
            try {
                orderQuantity++;
                OrderLog.addNewOrder(generationOfOrders());

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
                    order = new Order(Food.Pizza.PEPPERONI);
                    break;
                case 1:
                    order = new Order(Food.Pizza.HAM_AND_MUSHROOMS);
                    break;
                default:
                    order = new Order(Food.Pizza.MARGARITA);
                    break;
            }
        }

        return order;


    }
}
