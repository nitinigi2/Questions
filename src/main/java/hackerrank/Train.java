package hackerrank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Train {

    private String trainNumber;
    private List<Coach> coaches;

    public Train(String trainNumber) {
        // Implement constructor
        this.trainNumber = trainNumber;
        this.coaches = new ArrayList<>();
    }

    public String getTrainNumber() {
        // Implement getter
        return trainNumber;
    }

    public List<Coach> getCoaches() {
        // Implement getter
        return coaches;
    }

    public void addCoach(Coach coach) {
        // Implement method to add a coach to the train
        coaches.add(coach);
    }

    public double getTotalPriceForBookedTickets() {
        // Implement method to get the total price for all booked tickets
        return coaches.stream()
                .flatMap(coach -> coach.getReservedSeats().stream())
                .mapToDouble(seat -> seat.getSeatClass().getFixedPrice())
                .sum();
    }

    public Map<SeatClass, Double> getTotalPriceForEachSeatTypeBooked() {
        // Implement method to get the total price for each seat type booked
        Map<SeatClass, Double> totalPriceForEachSeatType = new HashMap<>();
        totalPriceForEachSeatType.put(SeatClass.FIRST_CLASS, 0.0d);
        totalPriceForEachSeatType.put(SeatClass.SECOND_CLASS, 0.0d);
        for (Coach coach : coaches) {
            for (Seat seat : coach.getReservedSeats()) {
                SeatClass seatClass = seat.getSeatClass();
                totalPriceForEachSeatType.put(seatClass,
                        totalPriceForEachSeatType.getOrDefault(seatClass, 0.0) + seatClass.getFixedPrice());
            }
        }

        return totalPriceForEachSeatType;
    }

    public Seat reserveSeat(SeatClass seatClass, Passenger passenger) {
        Optional<Coach> optionalCoach = coaches.stream().filter(coach -> coach.getSeatClass().equals(seatClass)).findFirst();
        Coach coach = optionalCoach.get();
        Optional<Seat> seat = coach.getUnReservedSeats().stream().findFirst();
        if(seat.isPresent()) {
            seat.get().reserveSeat(passenger);
        }
        return seat.get();
    }

    public int getTotalPassengersForSeatType(SeatClass seatClass) {
        // Implement method to get the total number of passengers who have booked a particular seat type
        return (int) coaches.stream()
                .flatMap(coach -> coach.getReservedSeats().stream())
                .filter(seat -> seat.getSeatClass() == seatClass)
                .count();
    }
}
