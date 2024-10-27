import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Booking> bookingHistory;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.bookingHistory = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    // Method to verify if the given password matches the user's password
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void addBooking(Booking booking) {
        bookingHistory.add(booking);
    }

    // New method to get the user's booking history
    public List<Booking> getBookingHistory() {
        return bookingHistory;
    }

    public void viewBookingHistory() {
        if (bookingHistory.isEmpty()) {
            System.out.println(username + " has no booking history.");
        } else {
            System.out.println(username + "'s Booking History:");
            for (Booking booking : bookingHistory) {
                String status = booking.isCanceled() ? "(Canceled)" : "(Active)";
                System.out.println("Movie: " + booking.getShowtime().getMovie().getTitle() +
                        ", Showtime: " + booking.getShowtime().getTime() + " " + status);
            }
        }
    }
}