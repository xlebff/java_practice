package Classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Post implements Interfaces.Post {
    private static List<Post> posts = new ArrayList<Post>();

    private final String id;
    private String title;
    private String content;

    private Classes.User author;
    private LocalDate createdAt;
    private int likes, dislikes, watches;
    private List<Classes.User> liked, disliked, watched;

    private List<Comment> comments;

    public Post(String title, String content, User author) {
        posts.add(this);
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = LocalDate.now();
        this.likes = 0;
        this.dislikes = 0;
        this.watches = 0;
        this.liked = new ArrayList<User>();
        this.disliked = new ArrayList<User>();
        this.watched = new ArrayList<User>();
        this.comments = new ArrayList<Comment>();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public int getWatches() {
        return watches;
    }

    public List<User> getLiked() {
        return liked;
    }

    public List<User> getDisliked() {
        return disliked;
    }

    public List<User> getWatched() {
        return watched;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void printComments() {
        System.out.println("Comments of post " + this + ":\n\n");
        for (Comment comment : comments) {
            System.out.println(comment.toString() + "\n\n");
        }
    }

    @Override
    public void like(User user) {
        if (!this.liked.contains(user)) {
            this.liked.add(user);
            ++likes;
            System.out.printf("Post %s liked by %s\n", this, user);
        } else removeRate(user);
    }

    @Override
    public void unlike(User user) {
        if (!this.disliked.contains(user)) {
            this.disliked.add(user);
            ++dislikes;
            System.out.printf("Post %s disliked by %s\n", this, user);
        } else removeRate(user);
    }

    @Override
    public void removeRate(User user) {
        if (this.liked.contains(user)) {
            --likes;
            this.liked.remove(user);
        } else if (this.disliked.contains(user)) {
            --dislikes;
            this.disliked.remove(user);
        } else {
            System.out.printf("User %s didn`t rate post %s.\n", user, this);
            return;
        }

        System.out.printf("%s`s rate was removed.\n", user);
    }

    @Override
    public void watch(User user) {
        if (!isAuthor(user)) {
            ++watches;
            if (!this.watched.contains(user)) watched.add(user);
        }

        System.out.println("\n--- Post " + this + " by " + this.author + " ---\n" +
                "Title: " + this.title + "\nContent: " + this.content + "\n");
    }

    @Override
    public void edit(User user, String title, String content) {
        if (isAuthor(user)) {
            this.title = title;
            this.content = content;
        } else System.out.println("Not permitted.\n");
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
    }

    public boolean isAuthor(User user) {
        return user.equals(this.author);
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
