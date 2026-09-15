package ParkingLot.domain;


public class ParkingSpot{
    int id; 
    VehicleType type;
    SpotStatus status; 
    int currentTicketID;
    Vehicle currentVehicle;


    public ParkingSpot(int id, VehicleType type) {
        this.id = id;
        this.type = type;
        this.status = SpotStatus.VACANT;
        this.currentTicketID = -1; // No ticket assigned initially
        this.currentVehicle = null;
    }

    public void setStatus(SpotStatus status) {
        this.status = status;
    }

    public void setCurrentTicketID(int ticketId) {
        this.currentTicketID = ticketId;
    }

    public void setVehicle(Vehicle vehicle) {
        this.currentVehicle = vehicle;
    }

    public void occupySpot(int ticketId) {
        this.status = SpotStatus.OCCUPIED;
        this.currentTicketID = ticketId;
    }

    public void vacantSpot(){
        this.status = SpotStatus.VACANT;
        this.currentTicketID = -1;
    }

    public VehicleType getVehicleType() {
        return this.type;
    }

    public boolean isOccupied(){
        return this.status == SpotStatus.OCCUPIED;
    }
}


