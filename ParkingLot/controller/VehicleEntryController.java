package ParkingLot.controller;


import ParkingLot.domain.*;
import ParkingLot.service.ParkingService;
import ParkingLot.dto.*;

public class VehicleEntryController {
    private ParkingService parkingService;

    public VehicleEntryController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    public EntryResult parkVehicle(Vehicle vehicle) {
        try {
            Ticket ticket = parkingService.parkVehicle(vehicle);
            return new EntryResult(ticket.getId(), "Vehicle parked successfully.", true);
        } catch (RuntimeException exception) {
            return new EntryResult(-1, exception.getMessage(), false);
        }
    }
}
