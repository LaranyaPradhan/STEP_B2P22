import java.util.*;

/**
 * UseCase6RoomAllocationService
 * 
 * This class demonstrates reservation confirmation and safe room allocation
 * using Queue, HashMap, and Set to prevent double-booking.
 * 
 * @author Laranya
 * @version 6.0
 */

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

// ----------- BOOKING QUEUE ------------

class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.offer(r);
    }

    public Reservation getNextRequest() {
        return queue.poll(); // removes from queue
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

// ----------- INVENTORY ------------

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decrementRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}

// ----------- BOOKING SERVICE ------------

class BookingService {

    // Tracks allocated room IDs per room type
    private Map<String, Set<String>> allocatedRooms = new HashMap<>();

    // Global set to ensure uniqueness
    private Set<String> usedRoomIds = new HashSet<>();

    // Generate unique room ID
    private String generateRoomId(String roomType) {
        String id;
        do {
            id = roomType.substring(0, 2).toUpperCase() + "-" + (int)(Math.random() * 1000);
        } while (usedRoomIds.contains(id));

        usedRoomIds.add(id);
        return id;
    }

    public void processBookings(BookingRequestQueue queue, RoomInventory inventory) {

        System.out.println("---- Processing Booking Requests ----\n");

        while (!queue.isEmpty()) {

            Reservation request = queue.getNextRequest();
            String type = request.getRoomType();

            int available = inventory.getAvailability(type);

            if (available > 0) {

                // Generate unique room ID
                String roomId = generateRoomId(type);

                // Store allocation
                allocatedRooms.putIfAbsent(type, new HashSet<>());
                allocatedRooms.get(type).add(roomId);

                // Update inventory immediately
                inventory.decrementRoom(type);

                // Confirm booking
                System.out.println("Booking CONFIRMED for " + request.getGuestName());
                System.out.println("Room Type: " + type + " | Room ID: " + roomId + "\n");

            } else {
                System.out.println("Booking FAILED for " + request.getGuestName());
                System.out.println("No available rooms for " + type + "\n");
            }
        }
    }
}

// ----------- MAIN CLASS ------------

public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("      Book My Stay Application         ");
        System.out.println("   Hotel Booking System - Version 6.0  ");
        System.out.println("=======================================\n");

        // Initialize components
        BookingRequestQueue queue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        BookingService bookingService = new BookingService();

        // Add booking requests
        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Single Room"));
        queue.addRequest(new Reservation("Charlie", "Single Room")); // should fail

        queue.addRequest(new Reservation("David", "Suite Room"));
        queue.addRequest(new Reservation("Eva", "Double Room"));
        queue.addRequest(new Reservation("Frank", "Double Room")); // should fail

        // Process bookings
        bookingService.processBookings(queue, inventory);
    }
}