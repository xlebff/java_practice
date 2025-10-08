package Interfaces;

public interface User {
    void addFriend(Classes.User user);
    void removeFriend(Classes.User user);

    void createPost(Classes.Post post);
    void deletePost(Classes.Post post);
}
