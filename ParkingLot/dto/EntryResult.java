package ParkingLot.dto;

public class EntryResult {
    private int ticketId;
    private String message;
    private boolean status;

    public EntryResult(int ticketId, String message, boolean status) {
        this.ticketId = ticketId;
        this.message = message;
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getMessage() {
        return message;
    }
}
