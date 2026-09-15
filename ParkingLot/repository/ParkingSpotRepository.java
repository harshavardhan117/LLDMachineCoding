package ParkingLot.repository;


import java.util.*;

import ParkingLot.domain.*;


public class ParkingSpotRepository {
    Map<ParkingFloor, List<ParkingSpot>> parkingSpots = new HashMap<>();

    public void addParkingSpot(ParkingFloor floor, ParkingSpot spot) {
        parkingSpots.computeIfAbsent(floor, k -> new ArrayList<>()).add(spot);
    }

    public List<ParkingSpot> getAllParkingSpots(ParkingFloor floor) {
        return parkingSpots.getOrDefault(floor, Collections.emptyList());
    }

    public void removeParkingSpot(ParkingFloor floor, ParkingSpot spot) {
        List<ParkingSpot> spots = parkingSpots.get(floor);
        if (spots != null) {
            spots.remove(spot);
            if (spots.isEmpty()) {
                parkingSpots.remove(floor);
            }
        }
    }

    public List<ParkingSpot> getAvailableParkingSpots(VehicleType type){
        List<ParkingSpot> availableSpots = new ArrayList<>();
        for (List<ParkingSpot> spots : parkingSpots.values()) {
            for (ParkingSpot spot : spots) {
                if (spot.getVehicleType() == type && !spot.isOccupied()) {
                    availableSpots.add(spot);
                }
            }
        }
        return availableSpots;
    }

    public void occupySpot(ParkingSpot spot, int ticketId, Vehicle vehicle) {
        spot.setStatus(SpotStatus.OCCUPIED);
        spot.setCurrentTicketID(ticketId);
        spot.setVehicle(vehicle);
    }

    public void vacantSpot(ParkingSpot spot) {
        spot.setStatus(SpotStatus.VACANT);
        spot.setCurrentTicketID(-1);
        spot.setVehicle(null);
    }
}
