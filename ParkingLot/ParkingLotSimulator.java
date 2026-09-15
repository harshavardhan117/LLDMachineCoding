package ParkingLot;

import ParkingLot.controller.*;
import ParkingLot.domain.*;
import ParkingLot.dto.*;
import ParkingLot.repository.*;
import ParkingLot.service.*;

public class ParkingLotSimulator {
    public static void main(String[] args) {
        // Create in-memory repositories.
        ParkingFloorRepository floorRepository = new ParkingFloorRepository();
        ParkingSpotRepository spotRepository = new ParkingSpotRepository();
        TicketRepository ticketRepository = new TicketRepository();
        PaymentRepository paymentRepository = new PaymentRepository();
        PricingRuleRepository pricingRuleRepository = new PricingRuleRepository();

        // Create services with their required repositories.
        FeeCalculatorService feeCalculatorService = new FeeCalculatorService(pricingRuleRepository);
        ParkingService parkingService = new ParkingService(
            spotRepository,
            ticketRepository,
            paymentRepository,
            feeCalculatorService
        );
        AdminService adminService = new AdminService(
            floorRepository,
            spotRepository,
            pricingRuleRepository
        );

        // Create controllers used by the client layer.
        AdminController adminController = new AdminController(adminService);
        VehicleEntryController entryController = new VehicleEntryController(parkingService);
        VehicleExitController exitController = new VehicleExitController(parkingService);

        // Set up one floor, one compatible spot, and a pricing rule.
        ParkingFloor floorB1 = new ParkingFloor(1);
        ParkingSpot carSpot = new ParkingSpot(1, VehicleType.CAR);
        adminController.addParkingFloor(floorB1);
        adminController.addParkingSpot(floorB1, carSpot);
        adminController.addPricingRule(new PricingRule(VehicleType.CAR, 10.0f, 100.0f, 0));

        // Simulate a vehicle entering and then exiting.
        EntryResult entryResult = entryController.parkVehicle(new Vehicle("ABC123", VehicleType.CAR));
        System.out.println(entryResult.getMessage());

        if (entryResult.getStatus()) {
            ExitResult exitResult = exitController.unparkVehicle(
                entryResult.getTicketId(),
                PaymentMethod.CREDIT_CARD
            );
            System.out.println(exitResult.getMessage());
        }
    }
}
