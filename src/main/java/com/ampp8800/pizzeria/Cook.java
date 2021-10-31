package com.ampp8800.pizzeria;

public class Cook extends Thread {
    private OrderManager orderManager = new OrderManager();
    private CompletedOrdersJournal completedOrdersJournal = CompletedOrdersJournal.getInstance();
    final static int TIME_OF_ORDER_SELECTION = 10;
    final static int MAXIMUM_QUEUE_TIME = 360;
    final static int COOKING_TIME = 60;
    final static int TIME_TO_RECHECK_QUEUE = 10;
    Order order;

    @Override
    public void run() {
        while ((ProgramsMethods.getNumberOfOrderGenerators() != 0) || (null != OrderQueueWrapper.getInstance().getQueueOrder().peekFirst())) {
            try {
                Thread.sleep(TIME_OF_ORDER_SELECTION * 1000);
                order = orderManager.takeQueue(MAXIMUM_QUEUE_TIME);
                if (null != order) {
                    synchronized (completedOrdersJournal) {
                        System.out.println(ProgramsMethods.currentDate() + "Cook " + getName() + " took the order " + order.getOrderNumber());
                        System.out.println(ProgramsMethods.currentDate() + "Order #" + order.getOrderNumber() + " " + order.getFood() + " " + ProgramsMethods.getPizzaComposition(order.getFood().getIngredients()));
                        if (null != completedOrdersJournal.getIncompleteOrder(order.getOrderNumber())) {
                            System.out.println(ProgramsMethods.currentDate() + "pizza is not cooked according to the recipe, not enough ingredients");
                        }
                        System.out.println(ProgramsMethods.currentDate() + "left in warehouse " + Warehouse.getInstance().getIngredientsInStock());
                    }
                    Thread.sleep(COOKING_TIME * 1000);
                    synchronized (completedOrdersJournal) {
                        System.out.println(ProgramsMethods.currentDate() + "Order #" + order.getOrderNumber() + " completed, " + order.getFood() + " " + ProgramsMethods.getPizzaComposition(order.getFood().getIngredients()));
                        if (null != completedOrdersJournal.getIncompleteOrder(order.getOrderNumber())) {
                            System.out.println(ProgramsMethods.currentDate() + "pizza is not cooked according to the recipe, not enough ingredients");
                        }
                    }
                } else {
                    Thread.sleep(TIME_TO_RECHECK_QUEUE * 1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
