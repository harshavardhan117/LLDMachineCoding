package ParkingLotDesign.entities;


public class ParkingSpot {
    private int id;
    private boolean isOccupied;
    //Shows the kind of vehicle type the spot has been designed to have. 
    private VehicleType vehicleType;
    private Vehicle vehicle;

    public ParkingSpot(int id, VehicleType vehicleType) {
        this.id = id;
        this.isOccupied = false;
        this.vehicleType = vehicleType;
        this.vehicle = null;
    }

    public int getId() {
        return id;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void parkVehicle(Vehicle vehicle){
        this.isOccupied = true;
        this.vehicle = vehicle;
    }

    public void removeVehicle(){
        this.isOccupied = false;
        this.vehicle = null;
    }

}
