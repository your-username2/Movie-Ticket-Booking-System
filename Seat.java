public class Seat {
    private int seatNumber;
    private boolean isBooked;

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.isBooked = false; // Default: Not booked
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void reserve() {
        if (!isBooked) {
            isBooked = true;
            System.out.println("Seat " + seatNumber + " reserved.");
        } else {
            System.out.println("Seat " + seatNumber + " is already booked.");
        }
    }

    public void release() {
        if (isBooked) {
            isBooked = false;
            System.out.println("Seat " + seatNumber + " released.");
        } else {
            System.out.println("Seat " + seatNumber + " is not booked.");
        }
    }
}