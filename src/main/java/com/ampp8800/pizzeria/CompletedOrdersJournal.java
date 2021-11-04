package com.ampp8800.pizzeria;

import java.util.*;

public class CompletedOrdersJournal {
    private static CompletedOrdersJournal completedOrdersJournal;
    private HashSet<Order> completedOrders = new HashSet<>();
    private Map<Integer, Queue<EnumIngredients.Ingredients>> incompleteOrders = new HashMap<>();

    private CompletedOrdersJournal() {
    }

    public static synchronized CompletedOrdersJournal getInstance() {
        if (completedOrdersJournal == null) {
            completedOrdersJournal = new CompletedOrdersJournal();
        }
        return completedOrdersJournal;
    }

    public void addNewOrder(Order order) {
        completedOrders.add(order);
    }

    public void notEnoughIngredientInOrder(Order order, EnumIngredients.Ingredients ingredient) {
        Queue<EnumIngredients.Ingredients> queue = new LinkedList<>();
        if (incompleteOrders.containsKey(order.getOrderNumber())) {
            queue = incompleteOrders.get(order.getOrderNumber());
        }
        queue.add(ingredient);
        incompleteOrders.put(order.getOrderNumber(), queue);
    }

    public Queue<EnumIngredients.Ingredients> getIncompleteOrder(Integer orderNumber) {
        return incompleteOrders.get(orderNumber);
    }
}
