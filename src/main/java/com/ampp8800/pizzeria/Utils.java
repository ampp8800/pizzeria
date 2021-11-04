package com.ampp8800.pizzeria;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        System.out.println(currentDate() + "the pizzeria will fulfill " + OrderQueueWrapper.THE_NUMBER_OF_ORDERS + " orders");
    }

    public static String currentDate() {
        Date date = new Date();
        return dateFormat.format(date);
    }

}
