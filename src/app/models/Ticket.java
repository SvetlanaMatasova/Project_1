package app.models;

public class Ticket {
    private static int idTicket;
    private int ticketNumber;
    private String issuedDate;
    private int flightNumber;
    public Ticket(){}
    public Ticket(int idTicket, int ticketNumber, String issuedDate, int flightNumber) {
        idTicket++;
        this.ticketNumber = ticketNumber;
        this.issuedDate = issuedDate;
        this.flightNumber = flightNumber;
    }

    public static int getIdTicket() {
        return idTicket;
    }

    public static void setIdTicket(int idTicket) {
        Ticket.idTicket = idTicket;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "idTicket=" + idTicket +
                ", ticketNumber=" + ticketNumber +
                ", issuedDate='" + issuedDate + '\'' +
                ", flightNumber=" + flightNumber +
                '}';
    }
}
