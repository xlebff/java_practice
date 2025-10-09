package Classes;

public class Comment {
    private User author;
    private String content;

    public Comment(User author, String content) {
        this.author = author;
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public void edit(User user, String content) {
        if (isAuthor(user)) {
            this.content = content;
            System.out.println("The content of comment was edited");
        }
    }

    private boolean isAuthor(User user) {
        return user.equals(author);
    }

    @Override
    public String toString() {
        return "Author: " + author + "\nContent: " + content + "\n";
    }
}
