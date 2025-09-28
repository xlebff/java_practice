import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ArrayList<Movie<Double>> movies = new ArrayList<Movie<Double>>();

        movies.add(new Movie<>("Инception", "Sci-Fi", 8.8));
        movies.add(new Movie<>("The Matrix", "Sci-Fi", 8.7));
        movies.add(new Movie<>("The Godfather", "Crime", 9.2));
        movies.add(new Movie<>("Goodfellas", "Crime", 8.7));
        movies.add(new Movie<>("The Shawshank Redemption", "Drama", 9.3));
        movies.add(new Movie<>("Forrest Gump", "Drama", 8.8));

        Map<String, List<Movie<Double>>> sortedMovies = groupByGenre(movies);

        for (Map.Entry<String, List<Movie<Double>>> entry : sortedMovies.entrySet()) {
            System.out.println("Genre: " + entry.getKey());
            for (Movie<Double> movie : entry.getValue()) {
                System.out.println("  - " + movie.getTitle());
            }
            System.out.println();
        }
    }

    public static Map<String, List<Movie<Double>>> groupByGenre(ArrayList<Movie<Double>> movies) {
        Map<String, List<Movie<Double>>> result = new HashMap<>();

        for (Movie<Double> movie : movies) {
            String genre = movie.getGenre();
            result.computeIfAbsent(genre, k -> new ArrayList<>()).add(movie);
        }

        return result;
    }
}