import java.util.*;

/**
 * UseCase9ErrorHandlingValidation
 * 
 * This class demonstrates input validation and error handling
 * using custom exceptions and fail-fast design.
 * 
 * @author Laranya
 * @version 9.0
 */

// ----------- CUSTOM EXCEPTION ------------

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// ----------- RESERVATION ------------

class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// ----------- INVENTORY ------------

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
    }

    public boolean isValidRoomType(String roomType) {
        return inventory.containsKey(roomType);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decrementRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}

// ----------- VALIDATOR ------------

class BookingValidator {

    public void validate(Reservation reservation, RoomInventory inventory)
            throws InvalidBookingException {

        // Validate room type
        if (!inventory.isValidRoomType(reservation.getRoomType())) {
            throw new InvalidBookingException("Invalid room type selected.");
        }

        // Validate availability
        if (inventory.getAvailability(reservation.getRoomType()) <= 0) {
            throw new InvalidBookingException("No rooms available for selected type.");
        }

        // Validate guest name
        if (reservation.getGuestName() == null || reservation.getGuestName().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }
    }
}

// ----------- BOOKING SERVICE ------------

class BookingService {

    private BookingValidator validator = new BookingValidator();

    public void confirmBooking(Reservation reservation, RoomInventory inventory) {

        try {
            // Fail-fast validation
            validator.validate(reservation, inventory);

            // Safe allocation
            inventory.decrementRoom(reservation.getRoomType());

            System.out.println("Booking CONFIRMED for " + reservation.getGuestName());
            System.out.println("Room Type: " + reservation.getRoomType() + "\n");

        } catch (InvalidBookingException e) {

            // Graceful failure handling
            System.out.println("Booking FAILED: " + e.getMessage() + "\n");
        }
    }
}

// ----------- MAIN CLASS ------------

public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("      Book My Stay Application         ");
        System.out.println("   Hotel Booking System - Version 9.0  ");
        System.out.println("=======================================\n");

        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService();

        // Valid booking
        Reservation r1 = new Reservation("Alice", "Single Room");

        // Invalid room type
        Reservation r2 = new Reservation("Bob", "Luxury Room");

        // No availability case
        Reservation r3 = new Reservation("Charlie", "Single Room");

        // Empty name
        Reservation r4 = new Reservation("", "Double Room");

        // Process bookings
        service.confirmBooking(r1, inventory);
        service.confirmBooking(r2, inventory);
        service.confirmBooking(r3, inventory);
        service.confirmBooking(r4, inventory);
    }
}