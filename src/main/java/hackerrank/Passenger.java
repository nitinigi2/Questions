package hackerrank;

import java.util.Random;
import java.util.UUID;

public class Passenger {

    private String name;
    private String ticketNumber;

    public Passenger(String name) {
        // Implement constructor
        this.ticketNumber = UUID.randomUUID().toString();
    }

    public String getName() {
        // Implement getter
        return this.name;
    }

    public String getTicketNumber() {
        // Implement getter
        return this.ticketNumber;
    }
}
