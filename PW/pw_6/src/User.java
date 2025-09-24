import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private int messagesCount;
    private int mentionsCount;
    private List<Message> messages = new ArrayList<Message>();

    public User(String name) {
        this.name = name;
        this.messagesCount = 0;
        this.mentionsCount = 0;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public String getName() {
        return name;
    }

    public int getMessagesCount() {
        return messagesCount;
    }

    public void incMessagesCount() {
        this.messagesCount++;
    }

    public int getMentionsCount() {
        return mentionsCount;
    }

    public void incMentionsCount() {
        this.mentionsCount++;
    }
}
