import Order.Order;
import Order.PurchaseOrder;
import Order.SalesOrder;
import Observer.Observer;
import Observer.Logger;

public class Main {
    public static void main(String[] args) {
        OrderManager manager = OrderManager.getInstance();
        Observer logger = new Logger();
        manager.addObserver(logger);

        System.out.println("\nСоздание заказов..");
        Order supply1 = manager.createPurchaseOrder("Samsung");
        Order supply2 = manager.createPurchaseOrder("Apple");
        Order sale1 = manager.createSalesOrder("Иван Петров");
        Order sale2 = manager.createSalesOrder("ООО Рога и копыта");

        supply1.addItem("Телевизор", 5, 50000);
        supply2.addItem("iPhone", 10, 80000);
        sale1.addItem("Телевизор", 1, 60000);
        sale2.addItem("iPhone", 2, 90000);

        System.out.println("\nОбработка заказов..");
        supply1.process();
        sale1.process();

        manager.printStatistics();

        System.out.println("\nВсе поставки:");
        manager.getPurchaseOrders().forEach(order -> {
            PurchaseOrder po = (PurchaseOrder) order;
            System.out.println("Поставка " + po.getId() + " от " + po.getSupplier());
        });

        System.out.println("\nВсе продажи:");
        manager.getSalesOrders().forEach(order -> {
            SalesOrder so = (SalesOrder)order;
            System.out.println("Заказ " + so.getId() + " от " + so.getCustomer());
        });
    }
}