import java.util.ArrayList;
import java.util.List;

public class Showtime {
    private Movie movie;
    private String time;
    private List<Seat> seats; // Updated to use Seat objects

    public Showtime(Movie movie, String time, int totalSeats) {
        this.movie = movie;
        this.time = time;
        this.seats = new ArrayList<>();

        // Initialize all seats as "Available"
        for (int i = 1; i <= totalSeats; i++) {
            seats.add(new Seat(i));
        }
    }

    public Movie getMovie() {
        return movie;
    }

    public String getTime() {
        return time;
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> availableSeats = new ArrayList<>();
        for (Seat seat : seats) {
            if (!seat.isBooked()) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }

    public void showDetails() {
        System.out.println("Movie: " + movie.getTitle() + ", Showtime: " + time);
        System.out.println("Available Seats: " + getAvailableSeats().size());
    }

    public void bookSeat(int seatNumber) {
        if (seatNumber >= 1 && seatNumber <= seats.size()) {
            seats.get(seatNumber - 1).reserve();
        } else {
            System.out.println("Invalid seat number.");
        }
    }
}