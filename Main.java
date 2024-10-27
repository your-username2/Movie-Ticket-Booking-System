import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Step 1: Create a Movie instance
        Movie movie1 = new Movie("Inception", "Sci-Fi", 148, 8.8);

        // Step 2: Create a Showtime for that movie
        Showtime showtime1 = new Showtime(movie1, "18:00", 10); // 10 seats available

        // Step 3: Create a User
        User user1 = new User("JohnDoe", "password123");

        // Show details before booking
        movie1.getDetails();
        showtime1.showDetails();

        // Step 4: Book seats using the Booking system
        List<Seat> seatsToBook = new ArrayList<>();
        seatsToBook.add(showtime1.getAvailableSeats().get(0)); // Seat 1
        seatsToBook.add(showtime1.getAvailableSeats().get(1)); // Seat 2

        Booking booking1 = new Booking(user1, showtime1, seatsToBook);

        // Step 5: Create a Payment and confirm the booking
        Payment payment = new CreditCardPayment(20.00, "1234-5678-9012-3456", "John Doe");
        booking1.confirmBooking(payment); // Confirm booking with payment

        // Step 6: Check the User's booking history
        user1.viewBookingHistory();

        // Step 7: Cancel the booking and check seats again
        booking1.cancelBooking();
        System.out.println("Available Seats After Cancelling Booking:");
        for (Seat seat : showtime1.getAvailableSeats()) {
            System.out.println("Seat " + seat.getSeatNumber() + " (Available)");
        }

        // Step 8: Check the User's booking history after cancellation
        user1.viewBookingHistory();
    }
}