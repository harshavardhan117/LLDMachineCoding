package ParkingLotDesign.entities;

import java.util.*;


public class ParkingLot {
    private int id;
    private List<ParkingSpot> parkingSpots;

    public ParkingLot(int id, List<ParkingSpot> parkingSpots) {
        this.id = id;
        this.parkingSpots = parkingSpots;
    }

    public ParkingLot(int id) {
        this.id = id;
        this.parkingSpots = new ArrayList<>();
    }

    public void removeParkingSpot(ParkingSpot spot) {
        parkingSpots.remove(spot);
    }

    public void addParkingSpot(ParkingSpot spot) {
        parkingSpots.add(spot);
    }

    public int getId() {
        return id;
    }   
    
    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public int getAvailableSpotsForVehicleType(VehicleType vehicleType) {
        int count = 0;
        for (ParkingSpot spot : parkingSpots) {
            if (!spot.isOccupied() && spot.getVehicleType() == vehicleType) {
                count++;
            }
        }
        return count;
    }

    public ParkingSpot getParkingSpotForVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : parkingSpots) {
            if (spot.isOccupied() && spot.getVehicle() == vehicle) {
                return spot;
            }
        }
        return null;
    }
}
