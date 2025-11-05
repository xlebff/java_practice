import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> items = new ArrayList<>();
    private final List<CartListener> listeners = new ArrayList<>();

    public void addCartListener(CartListener listener) {
        listeners.add(listener);
    }

    public void removeCartListener(CartListener listener) {
        listeners.remove(listener);
    }

    private void fireProductAdded(String productName, double price, int quantity) {
        CartEvent event = new CartEvent(this, productName, price, quantity);
        for (CartListener listener : listeners) {
            listener.productAdded(event);
        }
    }

    private void fireProductRemoved(String productName, double price, int quantity) {
        CartEvent event = new CartEvent(this, productName, price, quantity);
        for (CartListener listener : listeners) {
            listener.productRemoved(event);
        }
    }

    private void fireCartCleared() {
        CartEvent event = new CartEvent(this, "ALL", 0, 0);
        for (CartListener listener : listeners) {
            listener.cartCleared(event);
        }
    }

    public void addProduct(String productName, double price, int quantity) {
        items.add(new CartItem(productName, price, quantity));
        fireProductAdded(productName, price, quantity);
    }

    public void removeProduct(String productName) {
        items.removeIf(item -> {
            if (item.getName().equals(productName)) {
                fireProductRemoved(item.getName(), item.getPrice(), item.getQuantity());
                return true;
            }
            return false;
        });
    }

    public void clearCart() {
        items.clear();
        fireCartCleared();
    }

    public double getTotal() {
        return items.stream().mapToDouble(CartItem::getTotal).sum();
    }

    public int getItemCount() {
        return items.size();
    }

    private static class CartItem {
        private final String name;
        private final double price;
        private final int quantity;

        public CartItem(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getTotal() {
            return quantity * price;
        }
    }
}