package ParkingLot.repository;

import java.util.*;

import ParkingLot.domain.ParkingFloor;


public class ParkingFloorRepository {
    List<ParkingFloor> parkingFloors;

    public ParkingFloorRepository() {
        this.parkingFloors = new ArrayList<>();
    }

    public void addParkingFloor(ParkingFloor floor) {
        parkingFloors.add(floor);
    }

    public List<ParkingFloor> getAllParkingFloors() {
        return parkingFloors;
    }

    public ParkingFloor getParkingFloor(int id) {
        for (ParkingFloor floor : parkingFloors) {
            if (floor.getId() == id) {
                return floor;
            }
        }
        return null; // or throw an exception if not found
    }

    public void removeParkingFloor(int id) {
        parkingFloors.removeIf(floor -> floor.getId() == id);
    }

}
