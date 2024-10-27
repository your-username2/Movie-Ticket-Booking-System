public class Seat {
    private int seatNumber;
    private boolean isBooked;

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.isBooked = false;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void reserve() {
        this.isBooked = true;
    }

    public void release() {
        this.isBooked = false;
    }
}
