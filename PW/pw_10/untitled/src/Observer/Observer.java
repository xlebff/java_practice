package Observer;

import Order.Order;
import Order.Status;

public interface Observer {
    void onOrderCreated(Order order);
    void onOrderStatusChanged(Order order, Status oldStatus, Status newStatus);
    void onOrderProcessed(Order order);
}
