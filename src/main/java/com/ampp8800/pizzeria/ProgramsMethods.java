package com.ampp8800.pizzeria;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProgramsMethods {
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("[dd/MM/yyyy HH:mm:ss] ");
    private static int numberOfOrderGenerators = 0;

    public static int getNumberOfOrderGenerators() {
        return numberOfOrderGenerators;
    }

    public static void addedOrderGenerator() {
        ++ProgramsMethods.numberOfOrderGenerators;
    }

    public static void generationOfOrdersCompleted() {
        --ProgramsMethods.numberOfOrderGenerators;
    }

    public static void printTotalOrders() {
        System.out.println(currentDate() + "the pizzeria will fulfill " + OrderQueueWrapper.THE_NUMBER_OF_ORDERS + " orders");
    }

    public static String currentDate() {
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getPizzaComposition(EnumIngredients.Ingredients[] ingredients) {
        String result = "composition: ";
        for (EnumIngredients.Ingredients ingredient : ingredients) {
            result += ingredient +", ";
        }
        return result;
    }
}
