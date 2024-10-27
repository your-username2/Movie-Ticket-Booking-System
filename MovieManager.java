import java.util.ArrayList;
import java.util.List;

public class MovieManager {
    private List<Movie> movies;

    public MovieManager() {
        this.movies = new ArrayList<>();
    }

    // Add a new movie
    public void addMovie(String title, String genre, int duration, double rating) {
        Movie newMovie = new Movie(title, genre, duration, rating);
        movies.add(newMovie);
        System.out.println("Movie added successfully: " + title);
    }

    // Update an existing movie
    public void updateMovie(int index, String title, String genre, int duration, double rating) {
        if (index >= 0 && index < movies.size()) {
            Movie movie = movies.get(index);
            movie.setTitle(title);
            movie.setGenre(genre);
            movie.setDuration(duration);
            movie.setRating(rating);
            System.out.println("Movie updated successfully: " + title);
        } else {
            System.out.println("Invalid movie index.");
        }
    }

    // Delete a movie
    public void deleteMovie(int index) {
        if (index >= 0 && index < movies.size()) {
            Movie removedMovie = movies.remove(index);
            System.out.println("Movie deleted successfully: " + removedMovie.getTitle());
        } else {
            System.out.println("Invalid movie index.");
        }
    }

    // List all movies
    public void listMovies() {
        if (movies.isEmpty()) {
            System.out.println("No movies available.");
        } else {
            System.out.println("Movies:");
            for (int i = 0; i < movies.size(); i++) {
                Movie movie = movies.get(i);
                System.out.println(i + ". " + movie.getTitle() + " - " + movie.getGenre() + " (" + movie.getDuration() + " mins, Rating: " + movie.getRating() + ")");
            }
        }
    }

    // Get a movie by index
    public Movie getMovie(int index) {
        if (index >= 0 && index < movies.size()) {
            return movies.get(index);
        }
        return null;
    }
}