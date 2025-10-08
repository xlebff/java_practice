package Classes;

import Interfaces.Chat;

import java.util.ArrayList;
import java.util.List;

public class PublicChat extends Chat {
    private Classes.User admin;
    private String info;
    private List<User> blocked;

    public PublicChat(User admin, String title, String info) {
        super();
        this.admin = admin;
        this.membersCount = 1;
        this.members.add(admin);
        this.title = title;
        this.info = info;
        this.blocked = new ArrayList<User>();
    }

    private boolean isAdmin(User user) {
        if (user.equals(this.admin)) return true;
        else {
            System.out.printf("User %s is not an admin.\n", user);
            return false;
        }
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(User user, String title) {
        if (isAdmin(user)) {
            this.title = title;
            System.out.printf("Title of chat %s has changed.\n", this);
        } else System.out.println("Error: not permitted.");
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(User user, String info) {
        if (isAdmin(user)) {
            this.info = info;
            System.out.printf("Info of chat %s has changed.\n", this);
        } else System.out.println("Error: not permitted.");
    }

    public User getAdmin() {
        return admin;
    }

    public void changeAdmin(User curUser, User newAdmin) {
        if (isAdmin(curUser)) {
            if (members.contains(newAdmin)) {
                this.admin = newAdmin;
                System.out.printf("Admin of chat %s has changed. Now it`s %s.", this, newAdmin);
            }
            else System.out.printf("Error: user %s not found or blocked in chat %s\n", newAdmin, this.title);
        } else System.out.println("Error: not permitted.");
    }

    public List<User> getBlocked() {
        return blocked;
    }

    public boolean isBlocked(User user) {
        for (User member : members) {
            if (user.equals(member)) return true;
        }

        return false;
    }

    public void addBlocked(User curUser, User user) {
        if (isAdmin(curUser)) {
            this.members.remove(user);
            this.blocked.add(user);
            System.out.printf("User %s blocked.\n", user);
        }
    }

    public void removeBlocked(User user) {
        this.blocked.remove(user);
        System.out.printf("User %s unblocked.\n", user);
    }

    public void clearBlocked() {
        this.blocked.clear();
        System.out.println("Blocked list is clear now.");
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMember(User user) {
        if (isUserInChat(user)) {
            this.members.add(user);
            user.addPublicChat(this);
            System.out.printf("User %s added.\n", user);
        } else System.out.printf("User %s is already in chat %s.\n", user, this);
    }

    public void removeMember(User user) {
        if (isUserInChat(user)) {
            if (isAdmin(user)) {
                this.members.remove(user);
                user.removePublicChat(this);
            } else System.out.printf("Error: user %s is an admin.\n", user);
        }
    }
}
