package ParkingLot.repository;

import java.util.*;

import ParkingLot.domain.*;


public class TicketRepository {
    Map<Integer, Ticket> ticketMap;

    public TicketRepository() {
        this.ticketMap = new HashMap<>();
    }

    public void addTicket(Ticket ticket) {
        ticketMap.put(ticket.getId(), ticket);
    }

    public List<Ticket> getAllTickets() {
        return new ArrayList<>(ticketMap.values());
    }


    public Ticket findByID(int id){
        return ticketMap.get(id);
    }

    public void closeTicket(Ticket ticket) {
        ticket.closeTicket();
    }
}
