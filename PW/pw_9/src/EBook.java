public class EBook {
    private String title;
    private String author;
    private String genre;
    private int downloads;

    public EBook(String title, String author, String genre, int downloads) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.downloads = downloads;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getDownloads() {
        return downloads;
    }

    @Override
    public String toString() {
        return "Title: " + title +
                "\nAuthor: " + author +
                "\nGenre: " + genre +
                "\nDownloads: " + downloads;
    }
}
