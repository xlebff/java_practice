package Order;

public class SalesOrder extends AbstractOrder {
    private final String customer;

    public SalesOrder(String id, String customer) {
        super(id);
        this.customer = customer;
    }

    @Override
    public void process() {
        System.out.println("Отгружаем заказ для " + customer);
        this.status = Status.PROCESSING;
    }

    public String getCustomer() { return customer; }
}