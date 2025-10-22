package Decorator.Messages;

public abstract class MessageDecorator implements IMessage {
    protected Message message;

    public MessageDecorator(Message message) {
        this.message = message;
    }

    @Override
    public abstract void printMessage();
}
