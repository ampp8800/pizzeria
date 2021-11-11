package com.ampp8800.pizzeria;

import java.util.Map;

public class Supplier extends Thread {
    private final static int TIME_OF_SUPPLIER_VERIFICATION_PERIOD = 30 * 1000;
    private final static int MINIMUM_INGREDIENT_AMOUNT = 3;
    private final static int AMOUNT_OF_INGREDIENTS_TO_REPLENISH = 3;
    private Warehouse warehouse = Warehouse.getInstance();

    @Override
    public void run() {
        while (Utils.getNumberOfCooks() != 0) {
            try {
                Thread.sleep(TIME_OF_SUPPLIER_VERIFICATION_PERIOD);
                checkIngredientsAndAddMissingOnes();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void checkIngredientsAndAddMissingOnes() {
        for(Map.Entry<EnumIngredients.Ingredients, Integer> entry : warehouse.getIngredientsInStock().entrySet()) {
            Integer ingredientValue = entry.getValue();
            if (ingredientValue < MINIMUM_INGREDIENT_AMOUNT) {
                EnumIngredients.Ingredients ingredient = entry.getKey();
                warehouse.changeInNumberOfIngredientsInWarehouse(ingredient, true, AMOUNT_OF_INGREDIENTS_TO_REPLENISH);
                System.out.println(Utils.currentDate() + "Added to the warehouse " + AMOUNT_OF_INGREDIENTS_TO_REPLENISH + " " + ingredient);
            }
        }
    }

}
