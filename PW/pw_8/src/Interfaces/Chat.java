package Interfaces;

import Classes.Message;
import Classes.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public abstract class Chat {
    protected static List<Chat> chats = new ArrayList<Chat>();

    protected final String id;
    protected String title;

    protected List<User> members;
    protected int membersCount;

    protected List<Message> messages;

    public Chat() {
        chats.add(this);

        this.id = UUID.randomUUID().toString();
        this.messages = new ArrayList<Message>();
    }

    public boolean isUserInChat(User user) {
        for (User member : members) {
            if (user.equals(member)) return true;
        }

        return false;
    }

    public int getMembersCount() {
        return membersCount;
    }

    public List<User> getMembers() {
        return members;
    }

    public String getId() {
        return id;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void removeMessage(Message message) {
        messages.remove(message);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return Objects.equals(id, chat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
