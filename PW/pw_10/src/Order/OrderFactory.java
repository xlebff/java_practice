package Order;

public class OrderFactory {
    private int purchaseCounter = 1;
    private int salesCounter = 1;

    public Order createPurchaseOrder(String supplier) {
        String id = "PUR-" + (purchaseCounter++);
        PurchaseOrder order = new PurchaseOrder(id, supplier);
        System.out.println("Создан заказ на поставку: " + id);
        return order;
    }

    public Order createSalesOrder(String customer) {
        String id = "SAL-" + (salesCounter++);
        SalesOrder order = new SalesOrder(id, customer);
        System.out.println("Создан заказ на продажу: " + id);
        return order;
    }
}