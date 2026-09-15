package ParkingLot.service;

import ParkingLot.domain.*;
import ParkingLot.repository.*;


public class AdminService {
    public ParkingFloorRepository parkingFloorRepository;
    public ParkingSpotRepository parkingSpotRepository;
    public PricingRuleRepository pricingRuleRepository;

    public AdminService(ParkingFloorRepository parkingFloorRepository, ParkingSpotRepository parkingSpotRepository, PricingRuleRepository pricingRuleRepository) {
        this.parkingFloorRepository = parkingFloorRepository;
        this.parkingSpotRepository = parkingSpotRepository;
        this.pricingRuleRepository = pricingRuleRepository;
    }

    public void addParkingFloor(ParkingFloor floor) {
        parkingFloorRepository.addParkingFloor(floor);
    }

    public void addParkingSpot(ParkingFloor floor, ParkingSpot spot) {
        parkingSpotRepository.addParkingSpot(floor, spot);
    }

    public void addPricingRule(PricingRule rule) {
        pricingRuleRepository.addPricingRule(rule);
    }

    public void removeParkingFloor(int id) {
        parkingFloorRepository.removeParkingFloor(id);
    }

    public void removeParkingSpot(ParkingFloor floor, ParkingSpot spot) {
        parkingSpotRepository.removeParkingSpot(floor, spot);
    }

    public void updatePricingRule(PricingRule rule) {
        pricingRuleRepository.updatePricingRule(rule);
    }

}