import java.util.ArrayList;
import java.util.List;
import Booking.*;
import Movie.*;
import Payment.*;
import User.*;


public class Main {
    public static void main(String[] args) {
        // Sample Movies
        Movie movie1 = new Movie("Inception", "Sci-Fi", 148, 8.8);
        movie1.getDetails();

        // Admin creating showtime
        Showtime showtime = new Showtime(movie1, "18:00", 10);

        // User booking example
        User user = new User("johnDoe", "password123");
        List<Seat> seatsToBook = showtime.getAvailableSeats().subList(0, 2);

        Booking booking = new Booking(user, showtime, seatsToBook);
        booking.confirmBooking();

        // Process payment
        Payment payment = new CreditCardPayment(20.00);
        payment.processPayment();
    }
}
