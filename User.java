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

    public void addBooking(Booking booking) {
        bookingHistory.add(booking);
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