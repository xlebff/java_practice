public class Movie<T> {
    private String title;
    private String genre;
    private T rating;

    public Movie(String title, String genre, T rating) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public T getRating() {
        return rating;
    }

    public void setRating(T rating) {
        this.rating = rating;
    }
}
