package main.java.com.ampp8800.pizzeria;

public class OrderGenerator {

    private Order order;

    public Order getOrder() {
        return order;
    }

    public void generationOfOrders() throws InterruptedException {

        int roulette = ((int) (Math.random() * 7) + 3);
        Thread.sleep(roulette * 1000);
        roulette = (int) (Math.random() * 3);
        switch (roulette) {
            case 0:
                order = new Order(Food.Pizza.PEPPERONI);
                break;
            case 1:
                order = new Order(Food.Pizza.HAM_AND_MUSHROOMS);
                break;
            default:
                order = new Order(Food.Pizza.MARGARITA);
                break;
        }


    }
}
