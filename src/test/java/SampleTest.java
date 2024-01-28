import hackerrank.Coach;
import hackerrank.Passenger;
import hackerrank.Seat;
import hackerrank.SeatClass;
import hackerrank.Train;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SampleTest {

    private Train expressTrain;

    @BeforeEach
    void setUp() {
        expressTrain = new Train("Express123");

        // Create coaches with different seat classes
        Coach firstClassCoach = new Coach(1, SeatClass.FIRST_CLASS, 5);
        // Add seats to firstClassCoach

        Coach secondClassCoach = new Coach(2, SeatClass.SECOND_CLASS, 10);
        // Add seats to secondClassCoach

        // Add coaches to the train
        expressTrain.addCoach(firstClassCoach);
        expressTrain.addCoach(secondClassCoach);
    }

    @Test
    void testNumberOfSeatsInCoach() {
        List<Coach> coaches = expressTrain.getCoaches();
        assertEquals(2, coaches.size());
        List<Coach> firstClassCoaches = coaches.stream().filter(coach -> coach.getSeatClass().equals(SeatClass.FIRST_CLASS)).collect(Collectors.toList());
        List<Coach> secondClassCoaches = coaches.stream().filter(coach -> coach.getSeatClass().equals(SeatClass.SECOND_CLASS)).collect(Collectors.toList());
        assertEquals(1, firstClassCoaches.size());
        assertEquals(1, secondClassCoaches.size());

        firstClassCoaches.stream().forEach(coach -> {
            assertEquals(5, coach.getSeats().size());
        });

        secondClassCoaches.stream().forEach(coach -> {
            assertEquals(10, coach.getSeats().size());
        });
    }

    @Test
    void testSeatReservation() {
        Passenger passenger1 = new Passenger("John Doe");
        Seat seat = expressTrain.reserveSeat(SeatClass.FIRST_CLASS, passenger1);
        assertTrue(seat.isReserved());
        assertEquals(1, expressTrain.getTotalPassengersForSeatType(SeatClass.FIRST_CLASS));
    }

    @Test
    void testTotalPriceForBookedTickets() {
        Passenger passenger1 = new Passenger("John Doe");
        Passenger passenger2 = new Passenger("Alice Smith");
        expressTrain.reserveSeat(SeatClass.FIRST_CLASS, passenger1);
        expressTrain.reserveSeat(SeatClass.SECOND_CLASS, passenger2);

        double expectedTotalPrice = SeatClass.FIRST_CLASS.getFixedPrice() + SeatClass.SECOND_CLASS.getFixedPrice();
        assertEquals(expectedTotalPrice, expressTrain.getTotalPriceForBookedTickets());
    }

    @Test
    void testTotalPriceForEachSeatTypeBooked() {
        Passenger passenger1 = new Passenger("John Doe");
        Passenger passenger2 = new Passenger("Alice Smith");
        expressTrain.reserveSeat(SeatClass.FIRST_CLASS, passenger1);
        expressTrain.reserveSeat(SeatClass.SECOND_CLASS, passenger2);

        Map<SeatClass, Double> totalPriceForEachSeatType = expressTrain.getTotalPriceForEachSeatTypeBooked();
        assertEquals(SeatClass.FIRST_CLASS.getFixedPrice(), totalPriceForEachSeatType.get(SeatClass.FIRST_CLASS));
        assertEquals(SeatClass.SECOND_CLASS.getFixedPrice(), totalPriceForEachSeatType.get(SeatClass.SECOND_CLASS));
    }
}
