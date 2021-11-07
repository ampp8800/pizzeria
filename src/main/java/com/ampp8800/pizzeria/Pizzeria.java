package com.ampp8800.pizzeria;

public class Pizzeria {
    final static int NUMBER_OF_COOKS = 3;

    public static void main(String[] args) {
        Utils.printTotalOrders();

        Warehouse warehouse = Warehouse.getInstance();
        warehouse.fillWithIngredients();

        OrderGenerator orderGenerator = new OrderGenerator();
        orderGenerator.start();

        for (int i = 0; i < NUMBER_OF_COOKS; i++) {
            Cook cook = new Cook();
            cook.start();
        }
    }
}
