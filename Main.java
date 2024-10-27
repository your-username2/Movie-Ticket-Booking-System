import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize User Manager
        UserManager userManager = new UserManager();
        Scanner scanner = new Scanner(System.in);

        // User Registration & Login
        System.out.println("1. Register");
        System.out.println("2. Login");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        User currentUser = null;
        if (choice == 1) {
            // Registration flow
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            boolean registered = userManager.registerUser(username, password);
            if (registered) {
                currentUser = userManager.loginUser(username, password); // Auto login after registration
            }
        } else if (choice == 2) {
            // Login flow
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            currentUser = userManager.loginUser(username, password);
        }

        // Proceed if a valid user is logged in
        if (currentUser != null) {
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

            Booking booking1 = new Booking(currentUser, showtime1, seatsToBook);

            // Step 4: Create a Payment and confirm the booking
            Payment payment = new CreditCardPayment(20.00, "1234-5678-9012-3456", "John Doe");
            booking1.confirmBooking(payment); // Confirm booking with payment

            // Step 5: Check the User's booking history
            currentUser.viewBookingHistory();

            // Step 6: Cancel the booking and check seats again
            booking1.cancelBooking();
            System.out.println("Available Seats After Cancelling Booking:");
            for (Seat seat : showtime1.getAvailableSeats()) {
                System.out.println("Seat " + seat.getSeatNumber() + " (Available)");
            }

            // Step 7: Check the User's booking history after cancellation
            currentUser.viewBookingHistory();
        }

        // Close the scanner
        scanner.close();
    }
}