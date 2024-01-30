package app.models;

public class Flight {
    private static int idFlight;
    private int position;
    private int idFlightDestination;
    private String flightDate;
    private double priceOfSeat;
    private String originCity;
    private String DestinationCity;
    private String flightTime;
    int totalNumberOfSeats;
    public Flight() {}
public Flight( int idFlightDestination, String flightDate, double priceOfSeat, int totalNumberOfSeats) {
    idFlight++;
    this.position=idFlight;
    this.idFlightDestination = idFlightDestination;
    this.flightDate = flightDate;
    this.priceOfSeat = priceOfSeat;
    this.totalNumberOfSeats=totalNumberOfSeats;
}
    public Flight( int id, String originCity, String destinationCity, String flightDate, String flightTime, double priceOfSeat, int totalNumberOfSeats) {
        position=id;

        this.originCity = originCity;
        this.DestinationCity = destinationCity;
        this.flightTime = flightTime;
        this.flightDate = flightDate;
        this.priceOfSeat = priceOfSeat;
        this.totalNumberOfSeats=totalNumberOfSeats;
    }
    public Flight( int id, int idFlightDestination, String flightDate, double priceOfSeat) {
        position=id;
        this.idFlightDestination = idFlightDestination;
        this.flightDate = flightDate;
        this.priceOfSeat = priceOfSeat;
    }
    public int getIdFlight() {
        return this.position;
    }

    public static void setIdFlight(int idFlight) {
        Flight.idFlight = idFlight;
    }

    public int getIdFlightDestination() {
        return idFlightDestination;
    }

    public void setIdFlightDestination(int idFlightDestination) {
        this.idFlightDestination = idFlightDestination;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public double getPriceOfSeat() {
        return priceOfSeat;
    }

    public void setPriceOfSeat(double priceOfSeat) {
        this.priceOfSeat = priceOfSeat;
    }

    public String getOriginCity() {
        return originCity;
    }

    public String getDestinationCity() {
        return DestinationCity;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public int getTotalNumberOfSeats() {
        return totalNumberOfSeats;
    }

    @Override
    public String toString() {
        return getOriginCity()+"                                        "+getDestinationCity()+"                                        "+getFlightDate()+"                                        "+getFlightTime()+"                                        "+getPriceOfSeat();
    }
}
