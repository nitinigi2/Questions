import hackerrank.Coach;
import hackerrank.Passenger;
import hackerrank.Seat;
import hackerrank.SeatClass;
import hackerrank.Train;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HiddenTestCases {

    @Test
    void testTotalPriceForNoBookedTickets() {
        assertEquals(0.0, new Train("TestTrain").getTotalPriceForBookedTickets());
    }

    @Test
    void testTotalPriceForSingleBookedTicket() {
        Train train = new Train("TestTrain");
        Coach coach1 = new Coach(1, SeatClass.FIRST_CLASS, 5);
        train.addCoach(coach1);
        Passenger passenger = new Passenger("Bob");

        train.reserveSeat(SeatClass.FIRST_CLASS, passenger);

        assertEquals(SeatClass.FIRST_CLASS.getFixedPrice(), train.getTotalPriceForBookedTickets());
    }

    @Test
    void testTotalPriceForMultipleSeatClasses() {
        Train train = new Train("TestTrain");
        Coach coach1 = new Coach(1, SeatClass.FIRST_CLASS, 5);
        Coach coach2 = new Coach(1, SeatClass.SECOND_CLASS, 5);
        train.addCoach(coach1);
        train.addCoach(coach2);

        Passenger passenger1 = new Passenger("Alice");
        Passenger passenger2 = new Passenger("Charlie");

        train.reserveSeat(SeatClass.FIRST_CLASS, passenger1);
        train.reserveSeat(SeatClass.SECOND_CLASS, passenger2);

        double expectedTotalPrice = SeatClass.FIRST_CLASS.getFixedPrice() + SeatClass.SECOND_CLASS.getFixedPrice();
        assertEquals(expectedTotalPrice, train.getTotalPriceForBookedTickets());
    }

    @Test
    void testTotalPriceForMultipleSeatsInSameCoach() {
        Train train = new Train("TestTrain");
        Coach coach = new Coach(1, SeatClass.FIRST_CLASS, 5);
        train.addCoach(coach);

        Passenger passenger1 = new Passenger("Dave");
        Passenger passenger2 = new Passenger("Eve");

        train.reserveSeat(SeatClass.FIRST_CLASS, passenger1);
        train.reserveSeat(SeatClass.FIRST_CLASS, passenger2);

        double expectedTotalPrice = 2 * SeatClass.FIRST_CLASS.getFixedPrice();
        assertEquals(expectedTotalPrice, train.getTotalPriceForBookedTickets());
    }

    @Test
    void testTotalPriceForMultipleCoaches() {
        Train train = new Train("TestTrain");
        Coach coach1 = new Coach(1, SeatClass.FIRST_CLASS, 5);
        Coach coach2 = new Coach(2, SeatClass.SECOND_CLASS, 8);
        train.addCoach(coach1);
        train.addCoach(coach2);

        Passenger passenger1 = new Passenger("Frank");
        Passenger passenger2 = new Passenger("Grace");

        train.reserveSeat(SeatClass.FIRST_CLASS, passenger2);
        train.reserveSeat(SeatClass.SECOND_CLASS, passenger1);

        double expectedTotalPrice = SeatClass.FIRST_CLASS.getFixedPrice() + SeatClass.SECOND_CLASS.getFixedPrice();
        assertEquals(expectedTotalPrice, train.getTotalPriceForBookedTickets());
    }

    @Test
    void testTotalPriceForAllSeatsReservedInMultipleCoaches() {
        Train train = new Train("TestTrain");
        Coach coach1 = new Coach(1, SeatClass.FIRST_CLASS, 5);
        Coach coach2 = new Coach(2, SeatClass.SECOND_CLASS, 8);
        train.addCoach(coach1);
        train.addCoach(coach2);

        for (Seat seat : coach1.getSeats()) {
            Passenger passenger = new Passenger("Passenger");
            seat.reserveSeat(passenger);
        }

        for (Seat seat : coach2.getSeats()) {
            Passenger passenger = new Passenger("Passenger");
            seat.reserveSeat(passenger);
        }

        double expectedTotalPrice = 5 * SeatClass.FIRST_CLASS.getFixedPrice()
                + 8 * SeatClass.SECOND_CLASS.getFixedPrice();
        assertEquals(expectedTotalPrice, train.getTotalPriceForBookedTickets());
    }

    @Test
    void testTotalPriceForEachSeatTypeBookedWithMultipleSeats() {
        Train train = new Train("TestTrain");
        Coach coach = new Coach(1, SeatClass.FIRST_CLASS, 5);
        train.addCoach(coach);

        for (Seat seat : coach.getSeats()) {
            Passenger passenger = new Passenger("Passenger");
            seat.reserveSeat(passenger);
        }

        Map<SeatClass, Double> totalPriceForEachSeatType = train.getTotalPriceForEachSeatTypeBooked();
        assertEquals(5 * SeatClass.FIRST_CLASS.getFixedPrice(), totalPriceForEachSeatType.get(SeatClass.FIRST_CLASS));
    }

    @Test
    void testGetTotalPassengersForSeatTypeNoReservations() {
        Train train = new Train("TestTrain");
        int totalPassengersInFirstClass = train.getTotalPassengersForSeatType(SeatClass.FIRST_CLASS);
        int totalPassengersInSecondClass = train.getTotalPassengersForSeatType(SeatClass.SECOND_CLASS);
        assertEquals(0, totalPassengersInFirstClass);
        assertEquals(0, totalPassengersInSecondClass);
    }

    @Test
    void testGetTotalPassengersForSeatTypeWithReservations() {
        // Reserve seats in the first class coach
        Train expressTrain = new Train("TestTrain");
        Coach coach = new Coach(1, SeatClass.FIRST_CLASS, 5);
        expressTrain.addCoach(coach);
        Passenger passenger1 = new Passenger("John Doe");
        Passenger passenger2 = new Passenger("Alice Smith");
        expressTrain.reserveSeat(SeatClass.FIRST_CLASS, passenger1);
        expressTrain.reserveSeat(SeatClass.FIRST_CLASS, passenger2);

        int totalPassengersInFirstClass = expressTrain.getTotalPassengersForSeatType(SeatClass.FIRST_CLASS);
        assertEquals(2, totalPassengersInFirstClass);
    }

    @Test
    void testGetTotalPassengersForDifferentSeatType() {
        // Reserve seats in the second class coach
        Train expressTrain = new Train("TestTrain");
        Coach coach = new Coach(1, SeatClass.SECOND_CLASS, 5);
        expressTrain.addCoach(coach);
        Passenger passenger1 = new Passenger("Bob");
        Passenger passenger2 = new Passenger("Eve");
        expressTrain.reserveSeat(SeatClass.SECOND_CLASS, passenger1);
        expressTrain.reserveSeat(SeatClass.SECOND_CLASS, passenger2);

        int totalPassengers = expressTrain.getTotalPassengersForSeatType(SeatClass.SECOND_CLASS);
        assertEquals(2, totalPassengers);
        assertEquals(0, expressTrain.getTotalPassengersForSeatType(SeatClass.FIRST_CLASS));
    }

    @Test
    void testGetTotalPriceForEachSeatTypeBookedNoReservations() {
        Train expressTrain = new Train("TestTrain");
        Coach coach = new Coach(1, SeatClass.FIRST_CLASS, 5);
        expressTrain.addCoach(coach);
        Coach coach2 = new Coach(1, SeatClass.SECOND_CLASS, 5);
        expressTrain.addCoach(coach2);
        expressTrain.reserveSeat(SeatClass.FIRST_CLASS, new Passenger("John Doe"));

        // No reservations in the second class coach
        assertEquals(SeatClass.FIRST_CLASS.getFixedPrice(), expressTrain.getTotalPriceForEachSeatTypeBooked().get(SeatClass.FIRST_CLASS));
        assertEquals(0.0, expressTrain.getTotalPriceForEachSeatTypeBooked().get(SeatClass.SECOND_CLASS));
    }

    @Test
    void testGetTotalPriceForEachSeatTypeBookedWithReservations() {
        // Reserve seats in the first class coach
        Train expressTrain = new Train("TestTrain");
        Coach coach1 = new Coach(1, SeatClass.FIRST_CLASS, 5);
        Coach coach2 = new Coach(1, SeatClass.SECOND_CLASS, 5);
        expressTrain.addCoach(coach1);
        expressTrain.addCoach(coach2);
        Passenger passenger1 = new Passenger("John Doe");
        Passenger passenger2 = new Passenger("Alice Smith");
        expressTrain.reserveSeat(SeatClass.FIRST_CLASS, passenger1);
        expressTrain.reserveSeat(SeatClass.FIRST_CLASS, passenger2);

        // Reserve seats in the second class coach
        Seat seat3 = expressTrain.getCoaches().get(1).getSeats().get(0);
        Passenger passenger3 = new Passenger("Bob");
        expressTrain.reserveSeat(SeatClass.SECOND_CLASS, passenger3);

        assertEquals(2 * SeatClass.FIRST_CLASS.getFixedPrice(), expressTrain.getTotalPriceForEachSeatTypeBooked().get(SeatClass.FIRST_CLASS));
        assertEquals(SeatClass.SECOND_CLASS.getFixedPrice(), expressTrain.getTotalPriceForEachSeatTypeBooked().get(SeatClass.SECOND_CLASS));
    }

}
