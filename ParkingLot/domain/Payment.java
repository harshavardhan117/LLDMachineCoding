package ParkingLot.domain;

public class Payment {
    int id; 
    int ticketId;
    double amount;
    PaymentStatus status;
    PaymentMethod method;


    public Payment(int id, int ticketId, double amount, PaymentMethod method) {
        this.id = id;
        this.ticketId = ticketId;
        this.amount = amount;
        this.status = PaymentStatus.PENDING;
        this.method = method;
    }

    public void updatePaymentStatus(PaymentStatus newStatus) {
        this.status = newStatus;
        if (newStatus == PaymentStatus.COMPLETED) {
            System.out.println("Payment completed successfully");
        }
        else if(newStatus == PaymentStatus.FAILED){
            System.out.println("Payment failed");
        }
    }

    public int getId() {
        return id;
    }

    public int getTicketId() {
        return ticketId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public PaymentMethod getMethod() {
        return method;
    }
}
