package ParkingLot.domain;

import  java.util.*;

public class Ticket {
    int id; 
    Vehicle vehicle;
    ParkingSpot parkingSpot;
    Date issuedAt;
    Date endTime;


    public Ticket(int id, Vehicle vehicle, ParkingSpot parkingSpot) {
        this.id = id;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.issuedAt = new Date();
        this.endTime = null;
    }

    public void closeTicket() {
        if (endTime != null) {
            throw new IllegalStateException("Ticket is already closed.");
        }
        this.endTime = new Date();
    }

    public boolean isActive() {
        return endTime == null;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getId() {
        return id;
    }
    
    public Date getIssuedAt() {
        return issuedAt;
    }
}
