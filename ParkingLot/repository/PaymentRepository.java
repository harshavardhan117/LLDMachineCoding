package ParkingLot.repository;

import java.util.*;

import ParkingLot.domain.Payment;
import ParkingLot.domain.PaymentMethod;

public class PaymentRepository {
    List<Payment> payments;

    public PaymentRepository() {
        this.payments = new ArrayList<>();
    }

    public void addPayment(Payment payment) {
        PaymentMethod method = payment.getMethod();
        if(method == PaymentMethod.CREDIT_CARD) {
            payments.add(payment);
        }
        else if(method == PaymentMethod.UPI) {
            payments.add(payment);
        }
        else if(method == PaymentMethod.CASH) {
            payments.add(payment);
        }
        else{
            throw new IllegalArgumentException("Unsupported payment method: " + method);
        }
    }

    public List<Payment> getAllPayments() {
        return payments;
    }

    public Payment getPayment(int id) {
        for (Payment payment : payments) {
            if (payment.getId() == id) {
                return payment;
            }
        }
        return null; // or throw an exception if not found
    }
}
