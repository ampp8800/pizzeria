package com.ampp8800.pizzeria;

import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

public class OrderManager {
    private OrderQueueWrapper orderQueueWrapper = OrderQueueWrapper.getInstance();
    private Warehouse warehouse = Warehouse.getInstance();
    private CompletedOrdersJournal completedOrdersJournal = CompletedOrdersJournal.getInstance();

    public synchronized Order takeQueue(int MAXIMUM_QUEUE_TIME) {
        synchronized (orderQueueWrapper) {
            Deque<Order> unavailableOrders = new LinkedList<>();
            Order order = null;
            boolean orderTakenFromQueue = false;
            while (!orderTakenFromQueue) {
                if (null != orderQueueWrapper.getQueueOrder().peekFirst()) {
                    order = orderQueueWrapper.getQueueOrder().removeFirst();
                    orderTakenFromQueue = checkIngredientInStock(order);
                    if ((new Date().getTime() - order.getDate().getTime()) > MAXIMUM_QUEUE_TIME * 1000) {
                        orderTakenFromQueue = true;
                    }
                    if (orderTakenFromQueue) {
                        pickUpIngredientsFromWarehouse(order);
                        completedOrdersJournal.addNewOrder(order);
                    } else {
                        unavailableOrders.addLast(order);
                    }
                } else {
                    orderTakenFromQueue = true;
                    order = null;
                }
            }
            while (null != unavailableOrders.peekLast()) {
                orderQueueWrapper.getQueueOrder().addFirst(unavailableOrders.removeLast());
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

    public void pickUpIngredientsFromWarehouse(Order order) {
        EnumIngredients.Ingredients[] ingredients = order.getFood().getIngredients();
        Map<EnumIngredients.Ingredients, Integer> ingredientInStock = warehouse.getIngredientsInStock();
        int ingredientAmount;

        for (EnumIngredients.Ingredients ingredient : ingredients) {
            ingredientAmount = ingredientInStock.get(ingredient);
            if (ingredientAmount > 0) {
                ingredientInStock.put(ingredient, --ingredientAmount);
            } else {
                completedOrdersJournal.notEnoughIngredientInOrder(order, ingredient);
            }
        }
        warehouse.setIngredientsInStock(ingredientInStock);
    }

}
