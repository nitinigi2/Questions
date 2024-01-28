package hackerrank;

public class Seat {

    private int seatNumber;
    private boolean isReserved;
    private SeatClass seatClass;

    private Passenger passenger;

    public Seat(int seatNumber, SeatClass seatClass) {
        // Implement constructor
        this.seatNumber = seatNumber;
        this.seatClass = seatClass;
    }

    public int getSeatNumber() {
        // Implement getter
        return this.seatNumber;
    }

    public boolean isReserved() {
        // Implement getter
        return isReserved;
    }

    public void reserveSeat(Passenger passenger) {
        // Implement method to reserve the seat
        this.passenger = passenger;
        this.isReserved = true;
    }

    public double getTicketPrice() {
        // Implement getter
        return seatClass.getFixedPrice();
    }

    public SeatClass getSeatClass() {
        // Implement getter
        return seatClass;
    }
}
