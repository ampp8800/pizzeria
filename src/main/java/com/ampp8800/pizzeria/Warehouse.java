package com.ampp8800.pizzeria;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private static Warehouse warehouse;
    private Map<EnumIngredients.Ingredients, Integer> ingredientsInStock = new HashMap<>();

    private Warehouse() {
    }

    public static synchronized Warehouse getInstance() {
        if (warehouse == null) {
            warehouse = new Warehouse();
        }
        return warehouse;
    }

    public Map<EnumIngredients.Ingredients, Integer> getIngredientsInStock() {
        return ingredientsInStock;
    }

    public void setIngredientsInStock(Map<EnumIngredients.Ingredients, Integer> ingredientsInStock) {
        this.ingredientsInStock = ingredientsInStock;
    }

    public void fillWithIngredients() {
        ingredientsInStock.put(EnumIngredients.Ingredients.CHEESE, 5);
        ingredientsInStock.put(EnumIngredients.Ingredients.HAM, 5);
        ingredientsInStock.put(EnumIngredients.Ingredients.DOUGH, 5);
        ingredientsInStock.put(EnumIngredients.Ingredients.MUSHROOM, 5);
        ingredientsInStock.put(EnumIngredients.Ingredients.SAUSAGE, 5);
        ingredientsInStock.put(EnumIngredients.Ingredients.TOMATO, 5);
    }


}
