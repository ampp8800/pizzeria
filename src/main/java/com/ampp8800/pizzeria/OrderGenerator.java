package main.java.com.ampp8800.pizzeria;

public class OrderGenerator extends Thread{

    final int THE_NUMBER_OF_ORDERS = 11;
    private static int orderNumber = 0;

    @Override
    public void run() {
        while (orderNumber < THE_NUMBER_OF_ORDERS) {
            try {
                addNewOrder();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void addNewOrder() throws InterruptedException {
        orderNumber++;
        OrderLog.addNewOrder(orderNumber, generationOfOrders());
    }

    public Order generationOfOrders() throws InterruptedException {

        int rouletteTime = ((int) (Math.random() * 7) + 3);
        Order order;
        Thread.sleep(rouletteTime * 1000);
        int rouletteFood = (int) (Math.random() * 3);
        switch (rouletteFood) {
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
        return order;


    }
}
