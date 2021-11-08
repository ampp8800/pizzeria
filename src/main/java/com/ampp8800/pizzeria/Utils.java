package com.ampp8800.pizzeria;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;

public class Utils {
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("[dd/MM/yyyy HH:mm:ss] ");
    private static int numberOfOrderGenerators = 0;

    public static int getNumberOfOrderGenerators() {
        return numberOfOrderGenerators;
    }

    public static void addedOrderGenerator() {
        ++Utils.numberOfOrderGenerators;
    }

    public static void generationOfOrdersCompleted() {
        --Utils.numberOfOrderGenerators;
    }

    public static void printTotalOrders() {
        System.out.println(currentDate() + "The pizzeria will fulfill " + OrderQueueWrapper.THE_NUMBER_OF_ORDERS + " orders");
    }

    public static String currentDate() {
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String outputAtEndOfWork() {
        String result = currentDate();
        Order longestOrder = searchForLongestOrder();
        result += "Average order waiting time " + searchForAverageOrderWaitingTime() + " seconds, ";
        result += "longest order #" + longestOrder.getOrderNumber() + " be in progress " + (longestOrder.getDate().getTime() / 1000) + " seconds, ";
        result += "percentage of incomplete orders " + (int) (findingPercentageOfIncompleteOrders() * 100) + "%";
        return result;
    }

    public static double searchForAverageOrderWaitingTime() {
        CompletedOrdersJournal completedOrders = CompletedOrdersJournal.getInstance();
        double averageOrderWaitingTime = 0.0;
        for(Order order : completedOrders.getCompletedOrders()) {
            int waitingTimeForOrder = (int) (order.getDate().getTime() / 1000);
            averageOrderWaitingTime += waitingTimeForOrder;
        }
        averageOrderWaitingTime /= OrderQueueWrapper.THE_NUMBER_OF_ORDERS;
        averageOrderWaitingTime = (double) Math.round(averageOrderWaitingTime * 100) / 100;
        return averageOrderWaitingTime;
    }

    public static Order searchForLongestOrder() {
        CompletedOrdersJournal completedOrders = CompletedOrdersJournal.getInstance();
        Order longestOrder = null;
        for(Order order : completedOrders.getCompletedOrders()) {
            if (longestOrder == null) {
                longestOrder = order;
            }else if (longestOrder.getDate().getTime() < order.getDate().getTime()) {
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
        percentageOfIncompleteOrders /= OrderQueueWrapper.THE_NUMBER_OF_ORDERS;
        percentageOfIncompleteOrders = (double) Math.round(percentageOfIncompleteOrders * 100) / 100;
        return percentageOfIncompleteOrders;
    }
}
