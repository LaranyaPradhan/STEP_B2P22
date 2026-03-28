import java.util.LinkedList;
import java.util.Queue;

/**
 * UseCase5BookingRequestQueue
 * 
 * This class demonstrates handling booking requests using
 * a Queue to ensure First-Come-First-Served (FIFO) processing.
 * 
 * @author Laranya
 * @version 5.0
 */

// ----------- RESERVATION CLASS ------------

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

    public void displayReservation() {
        System.out.println("Guest: " + guestName + " | Room Type: " + roomType);
    }
}

// ----------- BOOKING QUEUE ------------

class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add booking request
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Booking request added for " + reservation.getGuestName());
    }

    // View all requests (without removing)
    public void displayQueue() {
        System.out.println("\n---- Booking Request Queue ----\n");
        for (Reservation r : queue) {
            r.displayReservation();
        }
    }

    // Get next request (for future processing)
    public Reservation getNextRequest() {
        return queue.peek(); // does not remove
    }
}

// ----------- MAIN CLASS ------------

public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("      Book My Stay Application         ");
        System.out.println("   Hotel Booking System - Version 5.0  ");
        System.out.println("=======================================\n");

        // Initialize Queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Create booking requests
        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Suite Room");
        Reservation r3 = new Reservation("Charlie", "Double Room");

        // Add requests (FIFO order)
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Display queue
        bookingQueue.displayQueue();

        // Peek next request
        System.out.println("\nNext request to be processed:");
        Reservation next = bookingQueue.getNextRequest();
        if (next != null) {
            next.displayReservation();
        }
    }
}