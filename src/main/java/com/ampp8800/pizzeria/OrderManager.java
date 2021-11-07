package com.ampp8800.pizzeria;

import java.util.Date;
import java.util.Map;

public class OrderManager {
    private static OrderManager orderManager;
    private OrderQueueWrapper orderQueueWrapper = OrderQueueWrapper.getInstance();
    private Warehouse warehouse = Warehouse.getInstance();
    private CompletedOrdersJournal completedOrdersJournal = CompletedOrdersJournal.getInstance();

    public static OrderManager getInstance() {
        if (orderManager == null) {
            orderManager = new OrderManager();
        }
        return orderManager;
    }

    public synchronized Order getAnOrderFromQueue(int MAXIMUM_QUEUE_TIME) {
        synchronized (orderQueueWrapper) {
            boolean orderIsAvailableForExecution = false;
            Order order = orderQueueWrapper.getQueueOrder().peekFirst();
            if (order != null) {
                if (checkIfMaximumWaitingTime(order, MAXIMUM_QUEUE_TIME) || checkIngredientInStock(order)){
                    orderIsAvailableForExecution = true;
                }
                if (orderIsAvailableForExecution) {
                    order = orderQueueWrapper.getQueueOrder().removeFirst();
                    pickUpIngredientsFromWarehouse(order);
                    completedOrdersJournal.addNewOrder(order);
                } else {
                    order = null;
                }
            }
            return order;
        }
    }

    public boolean checkIngredientInStock(Order order) {
        EnumIngredients.Ingredients[] ingredients = order.getFood().getIngredients();
        Map<EnumIngredients.Ingredients, Integer> ingredientInStock = warehouse.getIngredientsInStock();

        for (EnumIngredients.Ingredients ingredient : ingredients) {
            if (ingredientInStock.get(ingredient) == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean checkIfMaximumWaitingTime(Order order, int MAXIMUM_QUEUE_TIME) {
        return (new Date().getTime() - order.getDate().getTime()) > MAXIMUM_QUEUE_TIME;
    }

    public void pickUpIngredientsFromWarehouse(Order order) {
        EnumIngredients.Ingredients[] ingredients = order.getFood().getIngredients();
        Map<EnumIngredients.Ingredients, Integer> ingredientInStock = warehouse.getIngredientsInStock();
        int ingredientAmount;

        for (EnumIngredients.Ingredients ingredient : ingredients) {
            ingredientAmount = ingredientInStock.get(ingredient);
            if (ingredientAmount > 0) {
                ingredientInStock.put(ingredient, --ingredientAmount);
            } else {
                completedOrdersJournal.addToQueueWithIncompleteOrders(order, ingredient);
            }
        }
        warehouse.setIngredientsInStock(ingredientInStock);
    }

}
