package AdditionalTask1;

public class Main {
    public static void main(String[] args) {
        Order order = new Order(100);

        order.processPayment(order.getAmount());
        order.processPayment(order.getAmount());

        System.out.println();

        order.setRefund();
        order.setRefund();

        System.out.println();

        order.processPayment(order.getAmount());
        order.processPayment(order.getAmount());
    }
}
