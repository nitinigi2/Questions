package hackerrank;

public class Reservation {

    private Train train;
    private Coach coach;
    private Seat seat;

    public Reservation(Train train, Coach coach, Seat seat) {
        // Implement constructor
        this.train = train;
        this.coach = coach;
        this.seat = seat;
    }

    public Train getTrain() {
        // Implement getter
        return this.train;
    }

    public Coach getCoach() {
        // Implement getter
        return this.coach;
    }

    public Seat getSeat() {
        // Implement getter
        return this.seat;
    }
}
