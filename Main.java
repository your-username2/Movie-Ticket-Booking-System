public class Main {
    public static void main(String[] args) {
        // Step 1: Create a Movie instance
        Movie movie1 = new Movie("Inception", "Sci-Fi", 148, 8.8);

        // Step 2: Create a Showtime for that movie
        Showtime showtime1 = new Showtime(movie1, "18:00", 10); // 10 seats available

        // Test the details
        movie1.getDetails();  // Should show movie details
        showtime1.showDetails(); // Should show showtime details and number of available seats

        // Optional: Print all available seats
        System.out.println("Available Seats:");
        for (String seat : showtime1.getAvailableSeats()) {
            System.out.println(seat);
        }
    }
}