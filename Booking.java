import java.util.List;

public class Booking {
    private String userName;
    private Showtime showtime;
    private List<Seat> seats;

    public Booking(String userName, Showtime showtime, List<Seat> seats) {
        this.userName = userName;
        this.showtime = showtime;
        this.seats = seats;
    }

    public void confirmBooking() {
        // Step 1: Check if all seats are available before booking
        boolean allSeatsAvailable = true;

        for (Seat seat : seats) {
            if (seat.isBooked()) {
                System.out.println("Booking failed. Seat " + seat.getSeatNumber() + " is already booked.");
                allSeatsAvailable = false;
                break; // Stop checking further if one seat is booked
            }
        }

        // Step 2: Reserve seats only if all are available
        if (allSeatsAvailable) {
            for (Seat seat : seats) {
                seat.reserve();
            }
            System.out.println("Booking confirmed for user " + userName);
        } else {
            System.out.println("Booking failed. Please select different seats.");
        }
    }

    public void cancelBooking() {
        for (Seat seat : seats) {
            seat.release();
        }
        System.out.println("Booking cancelled for user " + userName);
    }
}