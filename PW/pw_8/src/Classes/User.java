package Classes;

import Interfaces.Chat;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class User implements Interfaces.User {
    private static List<User> users = new ArrayList<User>();

    private final String id;
    private String username, email;
    private LocalDate registrationDate, dateOfBirth;
    private int age;
    private Gender gender;

    private List<User> friends;
    private List<User> blocked;

    private List<PublicChat> publicChats;
    private List<PersonalChat> personalChats;

    private List<Post> posts;

    public User(String username, String email, LocalDate dateOfBirth, Gender gender) {
        this.id = UUID.randomUUID().toString();
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.username = username;
        this.registrationDate = LocalDate.now();
        this.age = Period.between(dateOfBirth, LocalDate.now()).getYears();
        this.gender = gender;
        this.friends = new ArrayList<User>();
        this.blocked = new ArrayList<User>();
        this.publicChats = new ArrayList<PublicChat>();
        this.personalChats = new ArrayList<PersonalChat>();
        this.posts = new ArrayList<Post>();
        users.add(this);
    }

    public String getId() {
        return id;
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
    public void createPost(String title, String content) {
        for (Post post : this.posts) {
            if (post.getTitle().equals(title)) {
                System.out.println("Post with title " + title + " is already exists.");
                return;
            }
        }
        Post post = new Post(title, content, this);
        this.posts.add(post);
    }

    @Override
    public void deletePost(Post post) {
        if (post.isAuthor(this)) {
            this.posts.remove(post);
            post = null;
        }
    }

    public Post getLastPost() {
        return this.posts.getLast();
    }

    public Post getPostByTitle(String title) {
        for (Post post : this.posts) {
            if (post.getTitle().equals(title)) return post;
        }

        System.out.println("There is no posts with title " + title + ".");
        return null;
    }

    public void writeComment(Post post, String content) {
        Comment comment = new Comment(this, content);
        post.addComment(comment);
    }

    public void sendMessage(Chat chat, String content) {
        Message message = new Message(this, content);
        chat.addMessage(message);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public List<User> getFriends() {
        return friends;
    }

    public List<User> getBlocked() {
        return blocked;
    }

    public List<PublicChat> getPublicChats() {
        return publicChats;
    }

    public List<PersonalChat> getPersonalChats() {
        return personalChats;
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
