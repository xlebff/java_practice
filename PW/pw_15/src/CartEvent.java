import java.util.EventObject;

public class CartEvent extends EventObject {
    private final String productName;
    private final double price;
    private final int quantity;

    public CartEvent(Object source, String productName, double price, int quantity) {
        super(source);
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }
}
