package hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Coach {

    private int coachNumber;
    private SeatClass seatClass;
    private List<Seat> seats;

    public Coach(int coachNumber, SeatClass seatClass, int totalSeats) {
        // Implement constructor
        this.coachNumber = coachNumber;
        this.seatClass = seatClass;
        this.seats = new ArrayList<>();
        for (int i = 0; i < totalSeats; i++) {
            seats.add(new Seat(i, seatClass));
        }
    }

    public int getCoachNumber() {
        // Implement getter
        return this.coachNumber;
    }

    public SeatClass getSeatClass() {
        // Implement getter
        return this.seatClass;
    }

    public List<Seat> getSeats() {
        // Implement getter
        return this.seats;
    }

    public List<Seat> getReservedSeats() {
        // Implement getter for reserved seats
        return seats.stream().filter(Seat::isReserved).collect(Collectors.toList());
    }

    public List<Seat> getUnReservedSeats() {
        return seats.stream().filter(seat -> !seat.isReserved()).collect(Collectors.toList());
    }
}
