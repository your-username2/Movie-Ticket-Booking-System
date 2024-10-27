public class Booking {
    private User user;
    private Showtime showtime;
    private List<Seat> seats;

    public Booking(User user, Showtime showtime, List<Seat> seats) {
        this.user = user;
        this.showtime = showtime;
        this.seats = seats;
    }

    public void confirmBooking() {
        for (Seat seat : seats) {
            seat.reserve();
        }
        System.out.println("Booking confirmed for " + user.getUsername());
    }

    public void cancelBooking() {
        for (Seat seat : seats) {
            seat.release();
        }
        System.out.println("Booking cancelled for " + user.getUsername());
    }
}
