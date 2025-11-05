import java.util.EventListener;

public interface CartListener extends EventListener {
    void productAdded(CartEvent event);
    void productRemoved(CartEvent event);
    void cartCleared(CartEvent event);
}
