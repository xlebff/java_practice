package Classes;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class User implements Interfaces.User {
    private static List<User> users;

    private final String id;
    private String username, email;
    private LocalDate registrationDate, dateOfBirth;
    private int age;
    private Gender gender;

    private List<User> friends;
    private List<User> blocked;

    private List<PublicChat> publicChats;
    private List<PersonalChat> personalChats;
    private Favorite favorite;

    public User(String username, String email, LocalDate dateOfBirth, Gender gender) {
        this.id = UUID.randomUUID().toString();
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.username = username;
        this.registrationDate = LocalDate.now();
        this.age = Period.between(registrationDate, dateOfBirth).getYears();
        this.gender = gender;
    }

    @Override
    public void addFriend(User user) {
        if (!friends.contains(user)) friends.add(user);
        System.out.printf("User %s is %s`s friend.\n", user, this);
    }

    @Override
    public void removeFriend(User user) {
        friends.remove(user);
        System.out.printf("User %s isn`t %s`s friend.\n", user, this);
    }

    @Override
    public void createPost(Post post) {

    }

    @Override
    public void deletePost(Post post) {

    }

    public String getUsername() {
        return username;
    }

    public void addPublicChat(PublicChat chat) {
        if (!publicChats.contains(chat)) publicChats.add(chat);
    }

    public void removePublicChat(PublicChat chat) {
        publicChats.remove(chat);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return username;
    }
}
