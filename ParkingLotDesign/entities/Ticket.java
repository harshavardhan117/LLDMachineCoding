package ParkingLotDesign.entities;

public class Ticket {
    public int ticketId;
    public long issuedAt;
    
    public Vehicle vehicle;
    public ParkingSpot parkingSpot;

    private static int ticketCount = 0;

    
    public Ticket(Vehicle vehicle, ParkingSpot parkingSpot) {
        ticketCount++;
        this.ticketId = ticketCount;
        this.issuedAt = System.currentTimeMillis();
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
    }

    public int getTicketId() {
        return ticketId;
    }

    public long getIssuedAt() {
        return issuedAt;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public float getPrice(long currentTime) {
        long duration = currentTime - issuedAt;
        long halfHourMillis = 30L * 60 * 1000;
        long halfHourBlocks = (duration + halfHourMillis - 1) / halfHourMillis;
        VehicleType vehicleType = vehicle.getVehicleType();
        if(vehicleType == VehicleType.CAR) {
            return halfHourBlocks * 0.5f; 
        } else if(vehicleType == VehicleType.BIKE) {
            return halfHourBlocks * 0.2f; 
        } else if(vehicleType == VehicleType.TRUCK) {
            return halfHourBlocks * 1.0f; 
        }
        return 0.0f; 
    }
}
