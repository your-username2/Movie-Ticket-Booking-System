import java.util.List;

public class Booking {
    private User user;
    private Showtime showtime;
    private List<Seat> seats;
    private boolean isCanceled = false; // New field to track cancellation

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

        for (Seat seat : seats) {
            if (seat.isBooked()) {
                System.out.println("Booking failed. Seat " + seat.getSeatNumber() + " is already booked.");
                allSeatsAvailable = false;
                break;
            }
        }

        if (allSeatsAvailable) {
            for (Seat seat : seats) {
                seat.reserve();
            }
            System.out.println("Booking confirmed for user " + user.getUsername());
            user.addBooking(this);
            isCanceled = false; // Ensure it's marked as active when confirmed
        } else {
            System.out.println("Booking failed. Please select different seats.");
        }
    }

    public void cancelBooking() {
        for (Seat seat : seats) {
            seat.release();
        }
        System.out.println("Booking cancelled for user " + user.getUsername());
        isCanceled = true; // Mark as canceled
    }

    public boolean isCanceled() {
        return isCanceled;
    }
}