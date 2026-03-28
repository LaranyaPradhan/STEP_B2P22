import java.util.*;

/**
 * UseCase10BookingCancellation
 * 
 * This class demonstrates booking cancellation and inventory rollback
 * using Stack (LIFO) for controlled state reversal.
 * 
 * @author Laranya
 * @version 10.0
 */

// ----------- RESERVATION ------------

class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;
    private String roomId;
    private boolean isActive;

    public Reservation(String reservationId, String guestName, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
        this.isActive = true;
    }

    public String getReservationId() { return reservationId; }
    public String getRoomType() { return roomType; }
    public String getRoomId() { return roomId; }

    public boolean isActive() { return isActive; }
    public void cancel() { this.isActive = false; }

    public void display() {
        System.out.println("Reservation ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room Type: " + roomType +
                " | Room ID: " + roomId +
                " | Status: " + (isActive ? "Active" : "Cancelled"));
    }
}

// ----------- INVENTORY ------------

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 0);
        inventory.put("Double Room", 0);
    }

    public void incrementRoom(String roomType) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + 1);
    }

    public void displayInventory() {
        System.out.println("\n---- Current Inventory ----");
        for (Map.Entry<String, Integer> e : inventory.entrySet()) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
    }
}

// ----------- BOOKING HISTORY ------------

class BookingHistory {
    private Map<String, Reservation> history = new HashMap<>();

    public void addReservation(Reservation r) {
        history.put(r.getReservationId(), r);
    }

    public Reservation getReservation(String id) {
        return history.get(id);
    }

    public void displayAll() {
        System.out.println("\n---- Booking Records ----");
        for (Reservation r : history.values()) {
            r.display();
        }
    }
}

// ----------- CANCELLATION SERVICE ------------

class CancellationService {

    // Stack for rollback tracking
    private Stack<String> rollbackStack = new Stack<>();

    public void cancelBooking(String reservationId,
                              BookingHistory history,
                              RoomInventory inventory) {

        Reservation r = history.getReservation(reservationId);

        // Validation
        if (r == null) {
            System.out.println("Cancellation FAILED: Reservation not found.\n");
            return;
        }

        if (!r.isActive()) {
            System.out.println("Cancellation FAILED: Already cancelled.\n");
            return;
        }

        // Rollback logic (LIFO)
        rollbackStack.push(r.getRoomId());

        // Restore inventory
        inventory.incrementRoom(r.getRoomType());

        // Update reservation status
        r.cancel();

        System.out.println("Cancellation SUCCESS for Reservation ID: " + reservationId);
    }
}

// ----------- MAIN CLASS ------------

public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("      Book My Stay Application         ");
        System.out.println("   Hotel Booking System - Version 10.0 ");
        System.out.println("=======================================\n");

        // Initialize components
        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();
        CancellationService cancelService = new CancellationService();

        // Simulated confirmed bookings
        Reservation r1 = new Reservation("R1", "Alice", "Single Room", "SR-101");
        Reservation r2 = new Reservation("R2", "Bob", "Double Room", "DR-201");

        history.addReservation(r1);
        history.addReservation(r2);

        // Display initial state
        history.displayAll();

        // Perform cancellation
        cancelService.cancelBooking("R1", history, inventory);

        // Attempt invalid cancellation
        cancelService.cancelBooking("R1", history, inventory); // already cancelled
        cancelService.cancelBooking("R3", history, inventory); // not exists

        // Display final state
        history.displayAll();
        inventory.displayInventory();
    }
}