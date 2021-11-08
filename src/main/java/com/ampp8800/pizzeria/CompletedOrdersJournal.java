package com.ampp8800.pizzeria;

import java.util.*;

public class CompletedOrdersJournal {
    private static CompletedOrdersJournal completedOrdersJournal;
    private Set<Order> completedOrders = new HashSet<>();
    private Map<Integer, HashSet<EnumIngredients.Ingredients>> incompleteOrders = new HashMap<>();

    private CompletedOrdersJournal() {
    }

    public static synchronized CompletedOrdersJournal getInstance() {
        if (completedOrdersJournal == null) {
            completedOrdersJournal = new CompletedOrdersJournal();
        }
        return completedOrdersJournal;
    }

    public Map<Integer, HashSet<EnumIngredients.Ingredients>> getIncompleteOrders() {
        return incompleteOrders;
    }

    public HashSet<EnumIngredients.Ingredients> getIncompleteOrder(Integer orderNumber) {
        return incompleteOrders.get(orderNumber);
    }

    public Set<Order> getCompletedOrders() {
        return completedOrders;
    }

    public void addNewOrder(Order order) {
        completedOrders.add(order);
    }

    public void addToQueueWithIncompleteOrders(Order order, EnumIngredients.Ingredients ingredient) {
        HashSet<EnumIngredients.Ingredients> ingredientsUsed;
        if (incompleteOrders.containsKey(order.getOrderNumber())) {
            ingredientsUsed = incompleteOrders.get(order.getOrderNumber());
        } else {
            ingredientsUsed = new HashSet<>(Arrays.asList(order.getFood().getIngredients()));
        }
        ingredientsUsed.remove(ingredient);
        incompleteOrders.put(order.getOrderNumber(), ingredientsUsed);
    }



}
