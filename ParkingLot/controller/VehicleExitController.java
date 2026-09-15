package ParkingLot.controller;


import ParkingLot.domain.*;
import ParkingLot.dto.*;
import ParkingLot.service.*;

public class VehicleExitController {
    private ParkingService parkingService;

    public VehicleExitController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    public ExitResult unparkVehicle(int ticketId, PaymentMethod paymentMethod) {
        try {
            Receipt receipt = parkingService.unparkVehicle(ticketId, paymentMethod);
            return new ExitResult(ticketId, "Vehicle unparked successfully.", true);
        } catch (RuntimeException exception) {
            return new ExitResult(ticketId, exception.getMessage(), false);
        }
    }
}
