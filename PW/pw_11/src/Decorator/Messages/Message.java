package Decorator.Messages;

public class Message implements IMessage {
    private final String content;

    public Message(String content) {
        this.content = content;
    }

    @Override
    public void printMessage() {
        System.out.println(content);
    }
}
