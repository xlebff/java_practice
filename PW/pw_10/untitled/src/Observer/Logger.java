package Observer;

import Order.Order;
import Order.Status;

public class Logger implements Observer {
    public void onOrderCreated(Order order) {
        System.out.println("Лог: СОЗДАН " + order.getId() +
                " (" + order.getClass().getSimpleName() + ")");
    }

    public void onOrderStatusChanged(Order order, Status oldStatus, Status newStatus) {
        System.out.println("Лог: СТАТУС " + order.getId() + " " +
                oldStatus + " → " + newStatus);
    }

    public void onOrderProcessed(Order order) {
        System.out.println("Лог: ОБРАБОТАН " + order.getId());
    }
}
