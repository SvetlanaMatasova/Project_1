package app.models;

public class FlightDestination {
    private static int idFlightDestination;
    private String originCity;
    private String DestinationCity;
    private String flightTime;
    private int position;
    public FlightDestination(){}
    public FlightDestination(String originCity, String destinationCity, String flightTime) {
        idFlightDestination++;
        this.position=idFlightDestination;
        this.originCity = originCity;
        this.DestinationCity = destinationCity;
        this.flightTime = flightTime;
        System.out.println("idDestination: "+idFlightDestination);
    }
    public FlightDestination(int idDestination, String originCity, String destinationCity, String flightTime) {
        idFlightDestination=idDestination;
        this.position=idDestination;
        this.originCity = originCity;
        this.DestinationCity = destinationCity;
        this.flightTime = flightTime;
    }
    public static int getIdFlightDestination() {
        return idFlightDestination;
    }
    public int getIdDestination() {
        return this.position;
    }
    public static void setIdFlightDestination(int idFlightDestination) {
        FlightDestination.idFlightDestination = idFlightDestination;
    }
    public String getOriginCity() {
        return originCity;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public String getDestinationCity() {
        return DestinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        DestinationCity = destinationCity;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

    @Override
    public String toString() {
        return "    "+originCity +"                                "+ DestinationCity +"                                "+ flightTime+"    ";
    }
}
