package Order;

public class PurchaseOrder extends AbstractOrder {
    private final String supplier;

    public PurchaseOrder(String id, String supplier) {
        super(id);
        this.supplier = supplier;
    }

    @Override
    public void process() {
        System.out.println("Принимаем поставку от " + supplier);
        this.status = Status.PROCESSING;
    }

    public String getSupplier() { return supplier; }
}