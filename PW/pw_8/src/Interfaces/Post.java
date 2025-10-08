package Interfaces;

public interface Post {
    void like(Classes.User user);
    void unlike(Classes.User user);
    void removeRate(Classes.User user);

    void watch(Classes.User user);

    void edit(Classes.User user, String title, String content);
}
