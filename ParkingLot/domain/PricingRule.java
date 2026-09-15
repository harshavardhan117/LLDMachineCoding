package ParkingLot.domain;

public class PricingRule {
    VehicleType vehicleType;
    float pricePerHour;
    float dailyPrice;
    int gracePeriodMinutes;

    public PricingRule(VehicleType vehicleType, float pricePerHour, float dailyPrice, int gracePeriodMinutes) {
        this.vehicleType = vehicleType;
        this.pricePerHour = pricePerHour;
        this.dailyPrice = dailyPrice;
        this.gracePeriodMinutes = gracePeriodMinutes;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public float getPricePerHour() {
        return pricePerHour;
    }

    public float getDailyPrice() {
        return dailyPrice;
    }

    public int getGracePeriodMinutes() {
        return gracePeriodMinutes;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setPricePerHour(float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public void setDailyPrice(float dailyPrice) {
        this.dailyPrice = dailyPrice;
    }
    
    public void setGracePeriodMinutes(int gracePeriodMinutes) {
        this.gracePeriodMinutes = gracePeriodMinutes;
    }
}
