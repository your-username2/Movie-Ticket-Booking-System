import java.util.List;

public class Booking {
    private User user; // Added reference to User
    private Showtime showtime;
    private List<Seat> seats;

    public Booking(User user, Showtime showtime, List<Seat> seats) {
        this.user = user;
        this.showtime = showtime;
        this.seats = seats;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public void confirmBooking() {
        boolean allSeatsAvailable = true;

        // Check if all seats are available before booking
        for (Seat seat : seats) {
            if (seat.isBooked()) {
                System.out.println("Booking failed. Seat " + seat.getSeatNumber() + " is already booked.");
                allSeatsAvailable = false;
                break;
            }
        }

        // Reserve seats only if all are available
        if (allSeatsAvailable) {
            for (Seat seat : seats) {
                seat.reserve();
            }
            System.out.println("Booking confirmed for user " + user.getUsername());
            user.addBooking(this); // Add booking to user's history
        } else {
            System.out.println("Booking failed. Please select different seats.");
        }
    }

    public void cancelBooking() {
        for (Seat seat : seats) {
            seat.release();
        }
        System.out.println("Booking cancelled for user " + user.getUsername());
    }
}