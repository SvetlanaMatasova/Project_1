package app;

import app.models.Flight;

import java.sql.Connection;
import java.util.ArrayList;

public class TravelAgency {
    private String originCity;
    private String DestinationCity;
    private int NumberSeats;
    private String Date1;
    private String Date2;
    ArrayList<Flight> result;
    ArrayList<Flight> resultRound;
    Connection conn;
    public TravelAgency(Connection conn, String originCity, String DestinationCity, int NumberSeats, String Date1, String Date2){
        this.originCity=originCity;
        this.DestinationCity=DestinationCity;
        this.NumberSeats=NumberSeats;
        this.Date1=Date1;
        this.Date2=Date2;
        this.conn=conn;

        if (originCity.equals("Original airport") && (DestinationCity.equals("Airport of destination")) && (Date1 != null)) TripDate();
        else if(Date1!=null) TripDateAndCountry();
        if(Date1==null) TripCountry();

        if ((Date1 != null) && (Date2 != null)) {
            TripDateAndCountry();
            TripRound();
        }
    }

    public ArrayList<Flight> getResult() {
        return result;
    }

    public ArrayList<Flight> getResultRound() {
        return resultRound;
    }

    public void TripDateAndCountry(){
        DbFunctions db = new DbFunctions();
        result=db.search_by_dateAndCountry(conn,originCity,DestinationCity,NumberSeats,Date1);
    }
    public void TripRound(){
        DbFunctions db = new DbFunctions();
        resultRound=db.search_by_dateAndCountry(conn,DestinationCity,originCity,NumberSeats,Date2);
    }
    public void TripCountry(){
        DbFunctions db = new DbFunctions();
        result=db.search_by_country(conn,originCity,DestinationCity,NumberSeats);
    }
    public void TripDate(){
        DbFunctions db = new DbFunctions();
       result=db.search_by_date(conn,Date1, NumberSeats);
    }
}
