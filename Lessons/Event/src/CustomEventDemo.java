import java.util.*;

class MessageEvent extends EventObject {
    private final String message;
    public MessageEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
    public String getMessage() { return message; }
}

interface MessageListener extends EventListener {
    void messageReceived(MessageEvent e);
}

class MessageSource {
    private final List<MessageListener> listeners = new ArrayList<>();

    public void addMessageListener(MessageListener l) { listeners.add(l); }
    public void removeMessageListener(MessageListener l) { listeners.remove(l); }

    public void fireMessage(String msg) {
        MessageEvent e = new MessageEvent(this, msg);
        for (MessageListener l : listeners) {
            l.messageReceived(e);
        }
    }
}

public class CustomEventDemo {
    public static void main(String[] args) {
        MessageSource source = new MessageSource();
        source.addMessageListener(e -> System.out.println("Получено сообщение: " + e.getMessage()));

        source.fireMessage("Привет, Swing!");
    }
}
