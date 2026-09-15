package ParkingLot.domain;

import java.util.*;

public class ParkingLot{

    int id; 
    List<ParkingFloor> floors;


    public ParkingLot(int id){
        this.id = id; 
        this.floors = new ArrayList<>();
    }

    public void addParkingFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public void removeParkingFloor(ParkingFloor floor) {
        floors.remove(floor);
    }
}
