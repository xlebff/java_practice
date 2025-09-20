public class Main {
    public static void main(String[] args) {
        Order[] orders = {
                new Order(0, 4, 1800.0),
                new Order(1, 3, 1500.0),
                new Order(2, 5, 2500.0),
                new Order(3, 2, 800.0),
                new Order(4, 7, 4200.0)
        };

        Processor<Order> printOrder = order ->
            System.out.println("Order ID: " + order.getOrderId() +
                                "\nItem count: " + order.getItemCount() +
                                "\nTotal cost: " + order.getTotalCost());

        double average = Order.getAverage(orders);
        Order.sortOrders(orders);

        System.out.println("Average: " + average + "\n");

        for(Order order : orders) {
            if(order.getTotalCost() > average) {
                printOrder.process(order);
                System.out.println();
            }
        }
    }
}
