package ParkingLot.dto;

public class ExitResult {
    private int ticketId;
    private String message;
    private boolean status; 

    public ExitResult(int ticketId, String message, boolean status) {
        this.ticketId = ticketId;
        this.message = message;
        this.status = status;
    }

    public int getTicketId() {
        return ticketId;
    }

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
