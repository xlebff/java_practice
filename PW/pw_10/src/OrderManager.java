import Observer.Observer;
import Order.OrderFactory;
import Order.Order;
import Order.PurchaseOrder;
import Order.SalesOrder;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static OrderManager instance;

    private List<Order> allOrders = new ArrayList<>();
    private final OrderFactory orderFactory = new OrderFactory();
    private List<Observer> observers = new ArrayList<>();

    private OrderManager() {
        System.out.println("OrderManager создан");
    }

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public Order createPurchaseOrder(String supplier) {
        Order order = orderFactory.createPurchaseOrder(supplier);
        allOrders.add(order);
        notifyOrderCreated(order);
        System.out.println("Менеджер: добавлен заказ на поставку " + order.getId());
        return order;
    }

    public Order createSalesOrder(String customer) {
        Order order = orderFactory.createSalesOrder(customer);
        allOrders.add(order);
        notifyOrderCreated(order);
        System.out.println("Менеджер: добавлен заказ на продажу " + order.getId());
        return order;
    }

    public List<Order> getAllOrders() {
        return allOrders;
    }

    public List<Order> getPurchaseOrders() {
        return allOrders.stream()
                .filter(order -> order instanceof PurchaseOrder)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public List<Order> getSalesOrders() {
        return allOrders.stream()
                .filter(order -> order instanceof SalesOrder)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public Order findOrderById(String id) {
        return allOrders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void printStatistics() {
        System.out.println("\nОтчёт:");
        System.out.println("Всего заказов: " + allOrders.size());
        System.out.println("Заказов на поставку: " + getPurchaseOrders().size());
        System.out.println("Заказов на продажу: " + getSalesOrders().size());
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
        System.out.println("Добавлен наблюдатель: " + observer.getClass().getSimpleName());
    }

    private void notifyOrderCreated(Order order) {
        for (Observer observer : observers) {
            observer.onOrderCreated(order);
        }
    }
}
