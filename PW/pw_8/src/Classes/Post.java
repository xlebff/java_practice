package Classes;

import java.time.LocalDate;
import java.util.List;

public class Post implements Interfaces.Post {
    private static List<Post> posts;

    private final int id;
    private String title;
    private String content;

    private Classes.User author;
    private LocalDate createdAt;
    private int likes, dislikes, watches;
    private List<Classes.User> liked, disliked, watched;
}
