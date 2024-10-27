import java.util.ArrayList;
import java.util.List;

public class ShowtimeManager {
    private List<Showtime> showtimes;

    public ShowtimeManager() {
        this.showtimes = new ArrayList<>();
    }

    // Add a new showtime for a specific movie
    public void addShowtime(Movie movie, String time, int seats) {
        Showtime newShowtime = new Showtime(movie, time, seats);
        showtimes.add(newShowtime);
        System.out.println("Showtime added successfully for " + movie.getTitle() + " at " + time);
    }

    // Update an existing showtime
    public void updateShowtime(int index, String time, int seats) {
        if (index >= 0 && index < showtimes.size()) {
            Showtime showtime = showtimes.get(index);
            showtime.setTime(time);
            showtime.setAvailableSeats(seats);
            System.out.println("Showtime updated successfully.");
        } else {
            System.out.println("Invalid showtime index.");
        }
    }

    // Delete a showtime
    public void deleteShowtime(int index) {
        if (index >= 0 && index < showtimes.size()) {
            Showtime removedShowtime = showtimes.remove(index);
            System.out.println("Showtime deleted successfully for " + removedShowtime.getMovie().getTitle() + " at " + removedShowtime.getTime());
        } else {
            System.out.println("Invalid showtime index.");
        }
    }

    // List all showtimes
    public void listShowtimes() {
        if (showtimes.isEmpty()) {
            System.out.println("No showtimes available.");
        } else {
            System.out.println("Showtimes:");
            for (int i = 0; i < showtimes.size(); i++) {
                Showtime showtime = showtimes.get(i);
                System.out.println(i + ". " + showtime.getMovie().getTitle() + " at " + showtime.getTime() + " (" + showtime.getAvailableSeats() + " seats)");
            }
        }
    }

    // Get a showtime by index
    public Showtime getShowtime(int index) {
        if (index >= 0 && index < showtimes.size()) {
            return showtimes.get(index);
        }
        return null;
    }

    public void listShowtimesForMovie(Movie movie) {
        for (Showtime showtime : showtimes) {
            if (showtime.getMovie().equals(movie)) {
                System.out.println(showtime.getTime());
            }
        }
    }
}