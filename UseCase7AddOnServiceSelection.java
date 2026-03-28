import java.util.*;

/**
 * UseCase7AddOnServiceSelection
 * 
 * This class demonstrates adding optional services to reservations
 * using Map and List without modifying core booking logic.
 * 
 * @author Laranya
 * @version 7.0
 */

// ----------- ADD-ON SERVICE ------------

class AddOnService {
    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

// ----------- SERVICE MANAGER ------------

class AddOnServiceManager {

    // Map<ReservationID, List of Services>
    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    // Add service to a reservation
    public void addService(String reservationId, AddOnService service) {

        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);

        System.out.println(service.getServiceName() + " added to Reservation " + reservationId);
    }

    // Display services for a reservation
    public void displayServices(String reservationId) {

        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services for Reservation " + reservationId);
            return;
        }

        System.out.println("\nServices for Reservation " + reservationId + ":");

        for (AddOnService s : services) {
            System.out.println("- " + s.getServiceName() + " ($" + s.getCost() + ")");
        }
    }

    // Calculate total cost
    public double calculateTotalCost(String reservationId) {

        List<AddOnService> services = serviceMap.get(reservationId);

        double total = 0;

        if (services != null) {
            for (AddOnService s : services) {
                total += s.getCost();
            }
        }

        return total;
    }
}

// ----------- MAIN CLASS ------------

public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("      Book My Stay Application         ");
        System.out.println("   Hotel Booking System - Version 7.0  ");
        System.out.println("=======================================\n");

        // Simulated reservation IDs (from Use Case 6)
        String res1 = "SR-101";
        String res2 = "DR-202";

        // Create services
        AddOnService breakfast = new AddOnService("Breakfast", 200);
        AddOnService spa = new AddOnService("Spa Access", 500);
        AddOnService pickup = new AddOnService("Airport Pickup", 300);

        // Manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Add services
        manager.addService(res1, breakfast);
        manager.addService(res1, spa);
        manager.addService(res2, pickup);

        // Display services
        manager.displayServices(res1);
        manager.displayServices(res2);

        // Cost calculation
        System.out.println("\nTotal Add-On Cost for " + res1 + ": $" + manager.calculateTotalCost(res1));
        System.out.println("Total Add-On Cost for " + res2 + ": $" + manager.calculateTotalCost(res2));
    }
}