import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Step 1: Create a Movie instance
        Movie movie1 = new Movie("Inception", "Sci-Fi", 148, 8.8);

        // Step 2: Create a Showtime for that movie
        Showtime showtime1 = new Showtime(movie1, "18:00", 10); // 10 seats available

        // Show details before booking
        movie1.getDetails();
        showtime1.showDetails();

        // Step 3: Book seats using the Booking system
        List<Seat> seatsToBook = new ArrayList<>();
        seatsToBook.add(showtime1.getAvailableSeats().get(0)); // Seat 1
        seatsToBook.add(showtime1.getAvailableSeats().get(1)); // Seat 2

        Booking booking1 = new Booking("JohnDoe", showtime1, seatsToBook);
        booking1.confirmBooking(); // Should confirm booking

        // Check available seats after booking
        System.out.println("Available Seats After Booking:");
        for (Seat seat : showtime1.getAvailableSeats()) {
            System.out.println("Seat " + seat.getSeatNumber() + " (Available)");
        }

        // Step 4: Attempt another booking, including a seat already booked
        List<Seat> seatsToBook2 = new ArrayList<>();
        seatsToBook2.add(showtime1.getAvailableSeats().get(0)); // Seat 1 - Already booked
        seatsToBook2.add(showtime1.getAvailableSeats().get(2)); // Seat 3

        Booking booking2 = new Booking("JaneDoe", showtime1, seatsToBook2);
        booking2.confirmBooking(); // Should fail because seat 1 is already booked

        // Cancel the first booking and check seats again
        booking1.cancelBooking();
        System.out.println("Available Seats After Cancelling Booking:");
        for (Seat seat : showtime1.getAvailableSeats()) {
            System.out.println("Seat " + seat.getSeatNumber() + " (Available)");
        }
    }
}