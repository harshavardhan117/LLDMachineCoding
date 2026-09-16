package ParkingLot.service.payment;

/**
 * Common contract for payment methods. Each implementation owns its own method-specific flow.
 */
public interface PaymentStrategy {
    void pay(double amount);
}
