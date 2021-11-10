package com.ampp8800.pizzeria;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Utils {
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("[dd/MM/yyyy HH:mm:ss] ");
    final static int THE_NUMBER_OF_ORDERS = 11;
    private static AtomicInteger numberOfOrderGenerators = new AtomicInteger();
    private static AtomicInteger numberOfCooks = new AtomicInteger();


    public static int getNumberOfOrderGenerators() {
        return numberOfOrderGenerators.get();
    }

    public static int getNumberOfCooks() {
        return numberOfCooks.get();
    }

    public static void incrementNumberOfOrderGenerators() {
        numberOfOrderGenerators.getAndIncrement();
    }

    public static void incrementNumberOfCooks() {
        numberOfCooks.getAndIncrement();
    }

    public static void decrementNumberOfOrderGenerators() {
        numberOfOrderGenerators.getAndDecrement();
    }

    public static void numberOfCooksCompleted() {
        numberOfCooks.getAndDecrement();
    }

    public static void printTotalOrders() {
        System.out.println(currentDate() + "The pizzeria will fulfill " + THE_NUMBER_OF_ORDERS + " orders");
    }

    public static String currentDate() {
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String outputAtEndOfWork() {
        String result = currentDate();
        Order longestOrder = searchForLongestOrder();
        result += "Average order waiting time " + searchForAverageOrderWaitingTime() + " seconds, ";
        result += "longest order #" + longestOrder.getOrderNumber() + " be in progress " + longestOrder.getTimeInQueueInSeconds() + " seconds, ";
        result += "percentage of incomplete orders " + (int) (findingPercentageOfIncompleteOrders() * 100) + "%";
        return result;
    }

    public static double searchForAverageOrderWaitingTime() {
        CompletedOrdersJournal completedOrders = CompletedOrdersJournal.getInstance();
        double averageOrderWaitingTime = 0.0;
        for(Order order : completedOrders.getCompletedOrders()) {
            averageOrderWaitingTime += order.getTimeInQueueInSeconds();
        }
        averageOrderWaitingTime /= THE_NUMBER_OF_ORDERS;
        averageOrderWaitingTime = (double) Math.round(averageOrderWaitingTime * 100) / 100;
        return averageOrderWaitingTime;
    }

    public static Order searchForLongestOrder() {
        CompletedOrdersJournal completedOrders = CompletedOrdersJournal.getInstance();
        Order longestOrder = null;
        for(Order order : completedOrders.getCompletedOrders()) {
            if (longestOrder == null) {
                longestOrder = order;
            }else if (longestOrder.getTimeInQueueInSeconds() < order.getTimeInQueueInSeconds()) {
                longestOrder = order;
            }
        }
        return longestOrder;
    }

    public static double findingPercentageOfIncompleteOrders() {
        CompletedOrdersJournal completedOrders = CompletedOrdersJournal.getInstance();
        double percentageOfIncompleteOrders = 0.0;

        for(Map.Entry<Integer, HashSet<EnumIngredients.Ingredients>> entry : completedOrders.getIncompleteOrders().entrySet()) {
            if (null != entry.getValue()) {
                percentageOfIncompleteOrders++;
            }
        }
        percentageOfIncompleteOrders /= THE_NUMBER_OF_ORDERS;
        percentageOfIncompleteOrders = (double) Math.round(percentageOfIncompleteOrders * 100) / 100;
        return percentageOfIncompleteOrders;
    }
}
