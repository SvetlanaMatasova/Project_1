package app.models;

public class Reservation {
    private static int idReservation;
    private int numberOfTickets;
    private int position;
    private int idFlight;
    private String originCity;
    private String DestinationCity;
    private String dateReservation;
    private String totalPrice;
    private int idUser;
    private String flightDate;
    private String payDate;
    boolean statusReservation;
    public Reservation(){}

    public Reservation(int numberOfTickets, int idFlight, String dateReservation, int idUser,String totalPrice) {
        idReservation++;
        this.position=idReservation;
        this.numberOfTickets = numberOfTickets;
        this.idFlight = idFlight;
        this.dateReservation = dateReservation;
        this.idUser = idUser;
        this.totalPrice=totalPrice;
    }
    public Reservation(int id, int numberOfTickets, int idFlight, String dateReservation, int idUser,String totalPrice, String originCity, String destinationCity,String flightDate) {
        this.position=id;
        this.numberOfTickets = numberOfTickets;
        this.idFlight = idFlight;
        this.dateReservation = dateReservation;
        this.idUser = idUser;
        this.totalPrice=totalPrice;
        this.originCity = originCity;
        this.DestinationCity = destinationCity;
        this.flightDate = flightDate;
    }
    public Reservation(int id, int numberOfTickets, int idFlight, String dateReservation, int idUser,String totalPrice, String originCity, String destinationCity,String flightDate,String payDate) {
        this.position=id;
        this.numberOfTickets = numberOfTickets;
        this.idFlight = idFlight;
        this.dateReservation = dateReservation;
        this.idUser = idUser;
        this.totalPrice=totalPrice;
        this.originCity = originCity;
        this.DestinationCity = destinationCity;
        this.flightDate = flightDate;
        this.payDate=payDate;

    }
    public Reservation(int numberOfTickets, int idFlight, String dateReservation, int idUser,String totalPrice, boolean statusReservation) {
        this.numberOfTickets = numberOfTickets;
        this.idFlight = idFlight;
        this.dateReservation = dateReservation;
        this.idUser = idUser;
        this.totalPrice=totalPrice;
        this.statusReservation=statusReservation;
        this.statusReservation=false;
    }

    public int getPosition() {
        return position;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public boolean isStatusReservation() {
        return statusReservation;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static int getIdReservation() {
        return idReservation;
    }

    public static void setIdReservation(int idReservation) {
        Reservation.idReservation = idReservation;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public int getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getOriginCity() {
        return originCity;
    }

    public String getDestinationCity() {
        return DestinationCity;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    @Override
    public String toString() {
        return  "   "+dateReservation+"                            "+originCity +"                            "+ DestinationCity +"                            "+flightDate+"                            " +numberOfTickets+"                            "+ totalPrice;
    }
    public String toStringWithPayment() {
        return  "   "+payDate+"                            "+originCity +"                            "+ DestinationCity +"                            "+flightDate+"                            " +numberOfTickets+"                            "+ totalPrice;
    }
}
