import java.util.ArrayList;
import java.util.List;

public class Showtime {
    private Movie movie;
    private String time;
    private List<Seat> seats;

    public Showtime(Movie movie, String time, int totalSeats) {
        this.movie = movie;
        this.time = time;
        this.seats = new ArrayList<>();
        for (int i = 1; i <= totalSeats; i++) {
            seats.add(new Seat(i));
        }
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> available = new ArrayList<>();
        for (Seat seat : seats) {
            if (!seat.isBooked()) {
                available.add(seat);
            }
        }
        return available;
    }

    public void bookSeat(int seatNumber) {
        if (seatNumber >= 1 && seatNumber <= seats.size()) {
            seats.get(seatNumber - 1).reserve();
        }
    }
}
