package Decorator.Messages;

public class SignedMessage extends MessageDecorator {
    private final String sender;

    public SignedMessage(Message message, String sender) {
        super(message);
        this.sender = sender;
    }

    @Override
    public void printMessage() {
        message.printMessage();
        System.out.println("Sender: " + sender);
    }
}
