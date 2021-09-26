package main.java.com.ampp8800.pizzeria;

public class Food {

    private static final String PEPPERONI = "Pepperoni";
    private static final String HAM_AND_MUSHROOMS = "HamAndMushrooms";
    private static final String MARGARITA = "Margarita";

    enum Pizza {
        PEPPERONI(Food.PEPPERONI, EnumIngredients.Ingredients.DOUGH, EnumIngredients.Ingredients.SAUSAGE, EnumIngredients.Ingredients.CHEESE),
        HAM_AND_MUSHROOMS(Food.HAM_AND_MUSHROOMS, EnumIngredients.Ingredients.DOUGH, EnumIngredients.Ingredients.HAM, EnumIngredients.Ingredients.MUSHROOM),
        MARGARITA(Food.MARGARITA, EnumIngredients.Ingredients.DOUGH, EnumIngredients.Ingredients.TOMATO, EnumIngredients.Ingredients.CHEESE);

        private String food;
        private EnumIngredients.Ingredients[] ingredients;


        Pizza(String food, EnumIngredients.Ingredients... ingredients) {
            this.food = food;
            this.ingredients = ingredients;
        }

        public EnumIngredients.Ingredients[] getIngredients() {
            return ingredients;
        }

        public String getFood() {
            return food;
        }
    }
}
