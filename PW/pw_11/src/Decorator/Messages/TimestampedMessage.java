package Decorator.Messages;

import java.time.LocalDate;

public class TimestampedMessage extends MessageDecorator {
    private final LocalDate timestamp;

    public TimestampedMessage(Message message, LocalDate timestamp) {
        super(message);
        this.timestamp = timestamp;
    }

    @Override
    public void printMessage() {
        message.printMessage();
        System.out.println("Время отправки: " + timestamp);
    }
}
