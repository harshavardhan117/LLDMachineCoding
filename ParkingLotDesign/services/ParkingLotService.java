package ParkingLotDesign.services;

import ParkingLotDesign.entities.*;



//This class handles the parking lot operations
//It should only take care of part, unpark, and get the status of whether a parking spots are occupied for a vehicle type.   
public class ParkingLotService {
    private ParkingLot parkingLot;
    
    public ParkingLotService(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    //Checks if atleast one spot is available. 
    public boolean isSpotAvailable(Vehicle vehicle){
        for(ParkingSpot spot : parkingLot.getParkingSpots()){
            if(!spot.isOccupied() && spot.getVehicleType() == vehicle.getVehicleType()){
                return true;
            }
        }
        return false;
    }

    public Ticket parkVehicle(Vehicle vehicle){
        for(ParkingSpot spot : parkingLot.getParkingSpots()){
            if(!spot.isOccupied() && spot.getVehicleType() == vehicle.getVehicleType()){
                spot.parkVehicle(vehicle);
                Ticket ticket = new Ticket(vehicle, spot);
                return ticket;
            }
        }
        return null;
    }

    

    // The ticket already contains the exact spot assigned during parking.
    public boolean unparkVehicle(Ticket ticket) {
        if (ticket == null) {
            return false;
        }

        ParkingSpot spot = ticket.getParkingSpot();
        if (!spot.isOccupied()) {
            return false;
        }

        spot.removeVehicle();
        return true;
    }

}
