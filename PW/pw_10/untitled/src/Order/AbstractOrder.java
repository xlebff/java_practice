package Order;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractOrder implements Order {
    protected String id;
    protected Status status = Status.CREATED;
    protected List<OrderItem> items = new ArrayList<>();

    public AbstractOrder(String id) {
        this.id = id;
    }

    @Override
    public String getId() { return id; }

    @Override
    public Status getStatus() { return status; }

    @Override
    public void updateStatus(Status status) { this.status = status; }

    @Override
    public void addItem(String product, int quantity, double price) {
        items.add(new OrderItem(product, quantity, price));
    }

    @Override
    public double calculateTotal() {
        double total = 0;

        for (OrderItem item : items) {
            total += (item.getPrice() * item.getQuantity());
        }

        return total;
    }
}

class OrderItem {
    private final String product;
    private final int quantity;
    private final double price;

    public OrderItem(String product, int quantity, double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
}