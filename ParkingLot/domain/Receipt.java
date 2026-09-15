package ParkingLot.domain;

import java.util.*;

public class Receipt {
    int id; 
    int ticketId;
    Date exitTime;
    double amount;
    PaymentStatus status;

    public Receipt(int id, int ticketId, Date exitTime, double amount, PaymentStatus status) {
        this.id = id;
        this.ticketId = ticketId;
        this.exitTime = exitTime;
        this.amount = amount;
        this.status = status;
    }

    public void printReceipt() {
        System.out.println("Receipt ID: " + id);
        System.out.println("Ticket ID: " + ticketId);
        System.out.println("Exit Time: " + exitTime);
        System.out.println("Amount: " + amount);
        System.out.println("Status: " + status);
    }

}
