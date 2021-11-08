package com.ampp8800.pizzeria;

public class Pizzeria {
    final static int NUMBER_OF_COOKS = 3;

    public static void main(String[] args) {
        String outputResult;
        Utils.printTotalOrders();

        Warehouse warehouse = Warehouse.getInstance();
        warehouse.fillWithIngredients();

        OrderGenerator orderGenerator = new OrderGenerator();
        orderGenerator.start();

        Supplier supplier = new Supplier();
        supplier.start();

        for (int i = 0; i < NUMBER_OF_COOKS; i++) {
            Cook cook = new Cook();
            cook.start();
        }
        try {
            supplier.join();
            outputResult = Utils.outputAtEndOfWork();
            System.out.println(outputResult);
            FileWorker.writeFile(outputResult);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
