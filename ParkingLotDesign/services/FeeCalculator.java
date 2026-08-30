package ParkingLotDesign.services;

import ParkingLotDesign.entities.*;

public class FeeCalculator {

    public double calculateFee(Ticket ticket) {
        long currentTime = System.currentTimeMillis();
        return ticket.getPrice(currentTime);
    }

}
