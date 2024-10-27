public class Main {
    public static void main(String[] args) {
        // Step 1: Create a Movie instance
        Movie movie1 = new Movie("Inception", "Sci-Fi", 148, 8.8);

        // Step 2: Create a Showtime for that movie
        Showtime showtime1 = new Showtime(movie1, "18:00", 10); // 10 seats available

        // Show details before booking
        movie1.getDetails();
        showtime1.showDetails();

        // Step 3: Book some seats
        showtime1.bookSeat(1); // Book seat 1
        showtime1.bookSeat(2); // Book seat 2

        // Step 4: Show available seats after booking
        System.out.println("Available Seats After Booking:");
        for (Seat seat : showtime1.getAvailableSeats()) {
            System.out.println("Seat " + seat.getSeatNumber() + " (Available)");
        }

        // Step 5: Try releasing a seat and recheck
        showtime1.bookSeat(1); // Try booking seat 1 again (should give a message it's already booked)
        showtime1.getAvailableSeats().get(0).release(); // Release seat 1
    }
}