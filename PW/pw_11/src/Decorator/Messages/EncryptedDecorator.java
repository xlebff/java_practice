package Decorator.Messages;

public class EncryptedDecorator extends MessageDecorator{
    public EncryptedDecorator(Message message) {
        super(message);
    }

    @Override
    public void printMessage() {
        System.out.println("Сообщение зашифровано.");
    }
}
