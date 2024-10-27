import java.util.ArrayList;
import java.util.List;

public class Showtime {
    private Movie movie;
    private String time;
    private List<String> seats; // Simple representation of seat availability

    public Showtime(Movie movie, String time, int totalSeats) {
        this.movie = movie;
        this.time = time;
        this.seats = new ArrayList<>();

        // Initialize all seats as "Available"
        for (int i = 1; i <= totalSeats; i++) {
            seats.add("Seat " + i + " (Available)");
        }
    }

    public Movie getMovie() {
        return movie;
    }

    public String getTime() {
        return time;
    }

    public List<String> getAvailableSeats() {
        List<String> availableSeats = new ArrayList<>();
        for (String seat : seats) {
            if (seat.contains("Available")) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }

    public void showDetails() {
        System.out.println("Movie: " + movie.getTitle() + ", Showtime: " + time);
        System.out.println("Available Seats: " + getAvailableSeats().size());
    }
}