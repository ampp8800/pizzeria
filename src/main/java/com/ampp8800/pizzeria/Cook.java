package com.ampp8800.pizzeria;

public class Cook extends Thread {
    private OrderManager orderManager = OrderManager.getInstance();
    private CompletedOrdersJournal completedOrdersJournal = CompletedOrdersJournal.getInstance();
    private final static int TIME_OF_ORDER_SELECTION = 5000;
    private final static int MAXIMUM_QUEUE_TIME = 30000;
    private final static int COOKING_TIME = 10000;
    private final static int TIME_TO_RECHECK_QUEUE = 10000;
    private Order order;

    @Override
    public void run() {
        while ((Utils.getNumberOfOrderGenerators() != 0) || (null != OrderQueueWrapper.getInstance().getQueueOrder().peekFirst())) {
            try {
                Thread.sleep(TIME_OF_ORDER_SELECTION);
                order = orderManager.takeQueue(MAXIMUM_QUEUE_TIME);
                if (order != null) {
                    synchronized (completedOrdersJournal) {
                        System.out.println(Utils.currentDate() + "Cook " + getName() + " took the order " + order.getOrderNumber());
                        System.out.println(Utils.currentDate() + "Order #" + order.getOrderNumber() + " " + order.getFood() +
                                " " + OrderManager.getPizzaComposition(order.getFood().getIngredients()));
                        if (null != completedOrdersJournal.getIncompleteOrder(order.getOrderNumber())) {
                            System.out.println(Utils.currentDate() + "pizza is not cooked according to the recipe, not enough ingredients");
                        }
                        System.out.println(Utils.currentDate() + "left in warehouse " + Warehouse.getInstance().getIngredientsInStock());
                    }
                    Thread.sleep(COOKING_TIME);
                    synchronized (completedOrdersJournal) {
                        System.out.println(Utils.currentDate() + "Order #" + order.getOrderNumber() + " completed, " +
                                order.getFood() + " " + OrderManager.getPizzaComposition(order.getFood().getIngredients()));
                        if (null != completedOrdersJournal.getIncompleteOrder(order.getOrderNumber())) {
                            System.out.println(Utils.currentDate() + "pizza is not cooked according to the recipe, not enough ingredients");
                        }
                    }
                } else {
                    Thread.sleep(TIME_TO_RECHECK_QUEUE);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
