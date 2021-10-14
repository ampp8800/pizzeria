package main.java.com.ampp8800.pizzeria;

public class OrderGenerator extends Thread{

    final int THE_NUMBER_OF_ORDERS = 11;
    private static int currentOrder = 0;
    private static int orderQuantity = 0;



    @Override
    public void run() {
        while (orderQuantity < THE_NUMBER_OF_ORDERS) {
            try {
                orderQuantity++;
                OrderLog.addNewOrder(generationOfOrders());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public synchronized Order generationOfOrders() throws InterruptedException {

        int rouletteTime = ((int) (Math.random() * 7) + 3);
        Order order;
        Thread.sleep(rouletteTime * 1000);
        int rouletteFood = (int) (Math.random() * 3);
        synchronized (this) {
            currentOrder++;
            switch (rouletteFood) {
                case 0:
                    order = new Order(currentOrder, Food.Pizza.PEPPERONI);
                    break;
                case 1:
                    order = new Order(currentOrder, Food.Pizza.HAM_AND_MUSHROOMS);
                    break;
                default:
                    order = new Order(currentOrder, Food.Pizza.MARGARITA);
                    break;
            }
        }

        return order;


    }
}
