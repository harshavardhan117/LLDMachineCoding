package ParkingLot.controller;

import ParkingLot.domain.*;
import ParkingLot.service.AdminService;



public class AdminController {
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    public void addParkingFloor(ParkingFloor floor) {
        adminService.addParkingFloor(floor);
    }

    public void addParkingSpot(ParkingFloor floor, ParkingSpot spot) {
        adminService.addParkingSpot(floor, spot);
    }

    public void addPricingRule(PricingRule rule) {
        adminService.addPricingRule(rule);
    }

    public void updatePricingRule(VehicleType vehicleType, float newPricePerHour) {
        PricingRule p = new PricingRule(vehicleType, newPricePerHour, newPricePerHour, 0);
        adminService.updatePricingRule(p);
    }


}
