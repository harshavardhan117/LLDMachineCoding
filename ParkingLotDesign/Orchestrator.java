package ParkingLotDesign;

import ParkingLotDesign.entities.*;
import ParkingLotDesign.services.*;
import java.util.ArrayList;
import java.util.List;

public class Orchestrator {
    public static void main(String[] args) {

        //Parking spot creator
        ParkingSpot spot1 = new ParkingSpot(1, VehicleType.CAR);
        ParkingSpot spot2 = new ParkingSpot(2, VehicleType.BIKE);
        ParkingSpot spot3 = new ParkingSpot(3, VehicleType.TRUCK);
        ParkingSpot spot4 = new ParkingSpot(4, VehicleType.CAR);
        ParkingSpot spot5 = new ParkingSpot(5, VehicleType.BIKE);
        ParkingSpot spot6 = new ParkingSpot(6, VehicleType.TRUCK);
        ParkingSpot spot7 = new ParkingSpot(7, VehicleType.CAR);
        ParkingSpot spot8 = new ParkingSpot(8, VehicleType.BIKE);
        ParkingSpot spot9 = new ParkingSpot(9, VehicleType.TRUCK);
        ParkingSpot spot10 = new ParkingSpot(10, VehicleType.CAR);
        ParkingSpot spot11 = new ParkingSpot(11, VehicleType.BIKE);
        ParkingSpot spot12 = new ParkingSpot(12, VehicleType.TRUCK);

        // Create a parking lot
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.addParkingSpot(spot1);
        parkingLot.addParkingSpot(spot2);
        parkingLot.addParkingSpot(spot3);
        parkingLot.addParkingSpot(spot4);
        parkingLot.addParkingSpot(spot5);
        parkingLot.addParkingSpot(spot6);
        parkingLot.addParkingSpot(spot7);
        parkingLot.addParkingSpot(spot8);
        parkingLot.addParkingSpot(spot9);
        parkingLot.addParkingSpot(spot10);
        parkingLot.addParkingSpot(spot11);
        parkingLot.addParkingSpot(spot12);

        ParkingLotService parkingLotService = new ParkingLotService(parkingLot);
        FeeCalculator feeCalculator = new FeeCalculator();

        List<Vehicle> incomingVehicles = List.of(
            new Vehicle("ABC123", VehicleType.CAR),
            new Vehicle("BIKE456", VehicleType.BIKE),
            new Vehicle("TRUCK789", VehicleType.TRUCK)
        );
        List<Ticket> activeTickets = new ArrayList<>();

        // Simulate vehicles entering the lot.
        for (Vehicle vehicle : incomingVehicles) {
            Ticket ticket = parkingLotService.parkVehicle(vehicle);
            if (ticket != null) {
                activeTickets.add(ticket);
                System.out.println("Vehicle parked successfully. Ticket ID: " + ticket.getTicketId());
            } else {
                System.out.println("Parking spot not available for " + vehicle.getLicensePlate());
            }
        }

        // Simulate vehicles exiting with their tickets.
        for (Ticket ticket : new ArrayList<>(activeTickets)) {
            double fee = feeCalculator.calculateFee(ticket);
            if (parkingLotService.unparkVehicle(ticket)) {
                activeTickets.remove(ticket);
                System.out.println("Parking fee for vehicle "
                    + ticket.getVehicle().getLicensePlate() + ": " + fee);
            }
        }
    }
}
