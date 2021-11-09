package com.ampp8800.pizzeria;

import java.util.ArrayList;

public class Pizzeria {
    final static int NUMBER_OF_COOKS = 3;

    public static void main(String[] args) {
        ArrayList<Cook> cooks = new ArrayList<>();
        String outputResult;
        Utils.printTotalOrders();

        Warehouse warehouse = Warehouse.getInstance();
        warehouse.fillWithIngredients();

        OrderGenerator orderGenerator = new OrderGenerator();
        orderGenerator.start();

        for (int i = 0; i < NUMBER_OF_COOKS; i++) {
            Cook cook = new Cook();
            cook.start();
            cooks.add(cook);
        }

        Supplier supplier = new Supplier();
        supplier.start();

        try {
            for (int i = 0; i < cooks.size(); i++) {
                cooks.get(i).join();
            }
            supplier.join();
            outputResult = Utils.outputAtEndOfWork();
            System.out.println(outputResult);
            FileWorker.writeFile(outputResult);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
