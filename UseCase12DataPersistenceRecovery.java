import java.io.*;
import java.util.*;

/**
 * UseCase12DataPersistenceRecovery
 * 
 * This class demonstrates persistence and recovery of system state
 * using serialization and file handling.
 * 
 * @author Laranya
 * @version 12.0
 */

// ----------- RESERVATION ------------

class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;

    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println("Reservation ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room Type: " + roomType);
    }
}

// ----------- INVENTORY ------------

class RoomInventory implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }

    public void displayInventory() {
        System.out.println("\n---- Inventory ----");
        for (Map.Entry<String, Integer> e : inventory.entrySet()) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }
}

// ----------- BOOKING HISTORY ------------

class BookingHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Reservation> history = new ArrayList<>();

    public void addReservation(Reservation r) {
        history.add(r);
    }

    public void displayHistory() {
        System.out.println("\n---- Booking History ----");
        for (Reservation r : history) {
            r.display();
        }
    }

    public List<Reservation> getHistory() {
        return history;
    }
}

// ----------- PERSISTENCE SERVICE ------------

class PersistenceService {

    private static final String FILE_NAME = "hotel_data.ser";

    // Save state
    public void save(BookingHistory history, RoomInventory inventory) {

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FILE_NAME))) {

            oos.writeObject(history);
            oos.writeObject(inventory);

            System.out.println("\nSystem state saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load state
    public Object[] load() {

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FILE_NAME))) {

            BookingHistory history = (BookingHistory) ois.readObject();
            RoomInventory inventory = (RoomInventory) ois.readObject();

            System.out.println("\nSystem state loaded successfully.");

            return new Object[]{history, inventory};

        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
            return null;
        }
    }
}

// ----------- MAIN CLASS ------------

public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("      Book My Stay Application         ");
        System.out.println("   Hotel Booking System - Version 12.0 ");
        System.out.println("=======================================\n");

        PersistenceService service = new PersistenceService();

        BookingHistory history;
        RoomInventory inventory;

        // Attempt recovery
        Object[] data = service.load();

        if (data != null) {
            history = (BookingHistory) data[0];
            inventory = (RoomInventory) data[1];
        } else {
            history = new BookingHistory();
            inventory = new RoomInventory();
        }

        // Simulate new booking
        history.addReservation(new Reservation("R101", "Alice", "Single Room"));

        // Display current state
        history.displayHistory();
        inventory.displayInventory();

        // Save state before shutdown
        service.save(history, inventory);
    }
}