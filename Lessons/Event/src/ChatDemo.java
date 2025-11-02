import java.util.*;

// Класс события чата
class ChatMessageEvent extends EventObject {
    private final String user;
    private final String text;

    public ChatMessageEvent(Object source, String user, String text) {
        super(source);
        this.user = user;
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }
}

// Интерфейс слушателя чата
interface ChatListener extends EventListener {
    void messageReceived(ChatMessageEvent e);
}

// Источник событий — сервер чата
class ChatServer {
    private final List<ChatListener> listeners = new ArrayList<>();

    public void addChatListener(ChatListener l) {
        listeners.add(l);
    }

    public void broadcast(String user, String text) {
        ChatMessageEvent event = new ChatMessageEvent(this, user, text);
        for (ChatListener l : listeners) {
            l.messageReceived(event);
        }
    }
}

// Главный класс с точкой входа
public class ChatDemo {
    public static void main(String[] args) {
        ChatServer server = new ChatServer();

        // Добавляем слушателя — он реагирует на новые сообщения
        server.addChatListener(e ->
                System.out.println("[" + e.getUser() + "]: " + e.getText())
        );

        // Генерируем события — пользователи "отправляют" сообщения
        server.broadcast("Alice", "Всем привет!");
        server.broadcast("Bob", "Привет, Alice!");
        server.broadcast("Charlie", "Как дела у всех?");
    }
}
