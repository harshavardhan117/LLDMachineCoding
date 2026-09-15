package ParkingLot.domain;

import java.util.*;

public class ParkingFloor {
    int id; 
    List<ParkingSpot> parkingSpots; 

    public ParkingFloor(int id){
        this.id = id; 
        this.parkingSpots = new ArrayList<>();
    }

    public void addParkingSpot(ParkingSpot spot) {
        parkingSpots.add(spot);
    }

    public void removeParkingSpot(ParkingSpot spot) {
        parkingSpots.remove(spot);
    }

    public int getId() {
        return id;
    }
}
