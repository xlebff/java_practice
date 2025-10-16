package Order;

public interface Order {
    String getId();
    Status getStatus();
    void updateStatus(Status newStatus);
    void addItem(String name, int quantity, double price);
    double calculateTotal();
    void process();
}
