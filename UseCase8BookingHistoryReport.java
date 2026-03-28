import java.util.*;

/**
 * UseCase8BookingHistoryReport
 * 
 * This class demonstrates booking history tracking and reporting
 * using List to maintain ordered records of confirmed reservations.
 * 
 * @author Laranya
 * @version 8.0
 */

// ----------- RESERVATION ------------

class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void display() {
        System.out.println("Reservation ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room Type: " + roomType);
    }
}

// ----------- BOOKING HISTORY ------------

class BookingHistory {

    // Stores confirmed bookings in order
    private List<Reservation> history = new ArrayList<>();

    // Add confirmed reservation
    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }

    // Retrieve all bookings
    public List<Reservation> getAllReservations() {
        return history;
    }

    // Display history
    public void displayHistory() {
        System.out.println("\n---- Booking History ----\n");
        for (Reservation r : history) {
            r.display();
        }
    }
}

// ----------- REPORT SERVICE ------------

class BookingReportService {

    // Generate summary report
    public void generateSummary(List<Reservation> reservations) {

        System.out.println("\n---- Booking Summary Report ----\n");

        Map<String, Integer> countMap = new HashMap<>();

        for (Reservation r : reservations) {
            countMap.put(
                r.getRoomType(),
                countMap.getOrDefault(r.getRoomType(), 0) + 1
            );
        }

        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            System.out.println(entry.getKey() + " Bookings: " + entry.getValue());
        }
    }
}

// ----------- MAIN CLASS ------------

public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("      Book My Stay Application         ");
        System.out.println("   Hotel Booking System - Version 8.0  ");
        System.out.println("=======================================\n");

        // Initialize history
        BookingHistory history = new BookingHistory();

        // Simulated confirmed bookings
        history.addReservation(new Reservation("SR-101", "Alice", "Single Room"));
        history.addReservation(new Reservation("DR-202", "Bob", "Double Room"));
        history.addReservation(new Reservation("SR-102", "Charlie", "Single Room"));
        history.addReservation(new Reservation("SU-301", "David", "Suite Room"));

        // Display booking history
        history.displayHistory();

        // Generate report
        BookingReportService reportService = new BookingReportService();
        reportService.generateSummary(history.getAllReservations());
    }
}