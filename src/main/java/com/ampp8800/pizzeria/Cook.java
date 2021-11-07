package com.ampp8800.pizzeria;

public class Cook extends Thread {
    private OrderManager orderManager = OrderManager.getInstance();
    private CompletedOrdersJournal completedOrdersJournal = CompletedOrdersJournal.getInstance();
    private final static int TIME_OF_ORDER_SELECTION = 5 * 1000;
    private final static int MAXIMUM_QUEUE_TIME = 30 * 1000;
    private final static int COOKING_TIME = 10 * 1000;
    private final static int TIME_TO_RECHECK_QUEUE = 10 * 1000;
    private Order order;

    @Override
    public void run() {
        while ((Utils.getNumberOfOrderGenerators() != 0) || (OrderQueueWrapper.getInstance().getQueueOrder().peekFirst() != null)) {
            try {
                Thread.sleep(TIME_OF_ORDER_SELECTION);
                order = orderManager.getAnOrderFromQueue(MAXIMUM_QUEUE_TIME);
                if (order != null) {
                    outputAtStartOfCooking();
                    Thread.sleep(COOKING_TIME);
                    outputAtEndOfCooking();
                } else {
                    Thread.sleep(TIME_TO_RECHECK_QUEUE);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void outputAtStartOfCooking() {
        if (completedOrdersJournal.getIncompleteOrder(order.getOrderNumber()) != null) {
            System.out.println(Utils.currentDate() + "Cook " + getName() + " took the order #" + order.getOrderNumber() +
                    ", " + order.getFood() + ". " + Order.getPizzaComposition(order.getFood().getIngredients()) +
                    " pizza is not cooked according to the recipe, not enough ingredients. Ingredients used " +
                    completedOrdersJournal.getIncompleteOrder(order.getOrderNumber()) + "Left in warehouse " +
                    Warehouse.getInstance().getIngredientsInStock());
        } else {
            System.out.println(Utils.currentDate() + "Cook " + getName() + " took the order #" + order.getOrderNumber() +
                    ", " + order.getFood() + ". " + Order.getPizzaComposition(order.getFood().getIngredients()) +
                    "Left in warehouse " + Warehouse.getInstance().getIngredientsInStock());
        }

    }

    private void outputAtEndOfCooking() {
        if (completedOrdersJournal.getIncompleteOrder(order.getOrderNumber()) != null) {
            System.out.println(Utils.currentDate() + "Order #" + order.getOrderNumber() + " completed, " +
                    order.getFood() + " " + Order.getPizzaComposition(order.getFood().getIngredients()) +
                    "pizza is not cooked according to the recipe, not enough ingredients");
        } else {
            System.out.println(Utils.currentDate() + "Order #" + order.getOrderNumber() + " completed, " +
                    order.getFood() + " " + Order.getPizzaComposition(order.getFood().getIngredients()));
        }
    }

}
