package ParkingLot.service;

import ParkingLot.repository.*;
import ParkingLot.service.payment.*;
import ParkingLot.domain.*;
import java.util.Date;

public class ParkingService {
    public ParkingSpotRepository parkingSpotRepository;
    public TicketRepository ticketRepository;
    public PaymentRepository paymentRepository;
    public FeeCalculatorService feeCalculatorService;
    private int nextReceiptId = 1;

    public ParkingService(ParkingSpotRepository parkingSpotRepository, TicketRepository ticketRepository, PaymentRepository paymentRepository, FeeCalculatorService feeCalculatorService) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.ticketRepository = ticketRepository;
        this.paymentRepository = paymentRepository;
        this.feeCalculatorService = feeCalculatorService;
    }

    /**
     * Owns the entry use case: choose a spot, reserve it, and persist the ticket.
     * The controller only asks for this operation; it does not allocate spots itself.
     */
    public Ticket parkVehicle(Vehicle vehicle) {
        java.util.List<ParkingSpot> availableSpots = parkingSpotRepository.getAvailableParkingSpots(vehicle.getType());
        if (availableSpots.isEmpty()) {
            throw new IllegalStateException("No compatible parking spot is available.");
        }

        // V1 allocation rule: take the first compatible vacant spot.
        ParkingSpot spot = availableSpots.get(0);
        int id = ticketRepository.getAllTickets().size()+1;
        Ticket ticket = new Ticket(id, vehicle, spot);
        parkingSpotRepository.occupySpot(spot, ticket.getId(), vehicle);
        ticketRepository.addTicket(ticket);
        return ticket;
    }

    /**
     * Owns the exit use case. A spot is released only after a completed payment.
     */
    public Receipt unparkVehicle(int ticketId, PaymentMethod method) {
        Ticket ticket = ticketRepository.findByID(ticketId);
        if (ticket == null || !ticket.isActive()) {
            throw new IllegalArgumentException("Ticket is invalid or already completed.");
        }

        double amount = feeCalculatorService.calculateTotalFees(ticket);
        int id = paymentRepository.getAllPayments().size()+1;
        Payment payment = new Payment(id, ticket.getId(), amount, method);
        // The factory hides which payment implementation is selected for the method.
        PaymentStrategy paymentStrategy = PaymentStrategyFactory.getPaymentStrategy(method);
        paymentStrategy.pay(amount);
        paymentRepository.addPayment(payment);
        payment.updatePaymentStatus(PaymentStatus.COMPLETED);

        if (payment.getStatus() != PaymentStatus.COMPLETED) {
            throw new IllegalStateException("Payment was not successful.");
        }

        ParkingSpot spot = ticket.getParkingSpot();
        parkingSpotRepository.vacantSpot(spot);
        ticketRepository.closeTicket(ticket);
        return new Receipt(nextReceiptId++, ticket.getId(), new Date(), amount, payment.getStatus());
    }

}

