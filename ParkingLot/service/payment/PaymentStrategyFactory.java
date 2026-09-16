package ParkingLot.service.payment;

import ParkingLot.domain.PaymentMethod;

public class PaymentStrategyFactory {

    // Keeps payment-method selection out of ParkingService.
    public static  PaymentStrategy getPaymentStrategy(PaymentMethod method) {
        switch (method) {
            case UPI:
                return new UpiPaymentStrategy();
            case CREDIT_CARD:
                return new CreditCardPaymentStrategy();
            case CASH:
                return new CashPaymentStrategy();
            default:
                throw new IllegalArgumentException("Unknown payment type");
        }
    }
}
