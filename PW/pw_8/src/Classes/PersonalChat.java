package Classes;

import Interfaces.Chat;

import java.util.ArrayList;
import java.util.List;

public class PersonalChat extends Chat {
    public PersonalChat(User firstUser, User secondUser) {
        super();
        this.membersCount = 2;
        this.members = new ArrayList<User>();
        this.members.add(firstUser);
        this.members.add(secondUser);
        this.title = firstUser + " & " + secondUser;
    }

    public List<Message> getMessages(User user) {
        if (isUserInChat(user)) return messages;
        else {
            System.out.println("Error: not permitted.");
            return null;
        }
    }
}
