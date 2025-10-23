package Decorator;

import Decorator.Messages.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<IMessage> messages = new ArrayList<>();
        messages.add(new SignedMessage(new Message("message"), "signer"));
        messages.add(new TimestampedMessage(new Message("message"), LocalDate.now()));
        messages.add(new EncryptedDecorator(new Message("message")));

        for (IMessage message : messages) {
            message.printMessage();
            System.out.println("\n---------\n");
        }
    }
}
