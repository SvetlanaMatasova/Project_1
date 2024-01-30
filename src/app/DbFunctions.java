package app;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import app.models.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class DbFunctions {
    public DbFunctions() {
    }
    public Connection connect_to_db(String dbname, String user, String pass) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
            if (conn != null) {
                System.out.println("Connection Established");
            } else {
                System.out.println("Connection Failed");
            }
        } catch (Exception var6) {
            System.out.println(var6);
        }
        return conn;
    }

    public void createTable(Connection conn, String table_name, String columns) {
        try {
            String query = "create table " + table_name + columns;
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");
        } catch (Exception var5) {
            System.out.println(var5);
        }
    }
    public void insert_row_flightToDB(Connection conn, Flight flight) {
        try {
            String query = String.format("insert into flights(idFlight,idFlightDestination,flightDate,priceOfSeat,totalNumberOfSeats) values('%s','%s','%s','%s','%s');", flight.getIdFlight(), flight.getIdFlightDestination(), flight.getFlightDate(),flight.getPriceOfSeat(), flight.getTotalNumberOfSeats());
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row flight Inserted");
        } catch (Exception var7) {
            System.out.println(var7);
        }
    }
    public void insert_row_userToDB(Connection conn, User user) {
        try {
            String query = String.format("insert into users(idUser,nameUser,passwordUser,email,phoneNumber,StatusManager) values('%s','%s','%s','%s','%s','%s');", user.getPosition(), user.getNameUser(), user.getPassword(),user.getEmail(), user.getPhoneNumber(),user.isStatusManager());
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row user Inserted");
        } catch (Exception var7) {
            System.out.println(var7);
        }
    }
    public void insert_row_destinationToDB(Connection conn, FlightDestination destination) {
        try {
            String query = String.format("insert into destinations(idDestination,originCity,destinationCity,flightTime) values('%s','%s','%s','%s');", destination.getIdDestination(), destination.getOriginCity(), destination.getDestinationCity(),destination.getFlightTime());
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row destination Inserted");
        } catch (Exception var7) {
            System.out.println(var7);
        }
    }

    public void insert_row_user(Connection conn, User user) {
        try {
            String query = String.format("insert into users(idUser,nameUser,email,passwordUser,phoneNumber,StatusManager) values('%s','%s','%s','%s','%s','%s');",User.getIdUser(), user.getNameUser(), user.getEmail(),user.getPassword(),user.getPhoneNumber(),user.isStatusManager());
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row user Inserted");
        } catch (Exception var7) {
            JOptionPane.showMessageDialog(null,var7,"Error",JOptionPane.WARNING_MESSAGE);
        }
    }
    public void insert_row_destination(Connection conn, FlightDestination destination) {
        try {
            String query = String.format("insert into destinations(idDestination,originCity,destinationCity,flightTime) values('%s','%s','%s','%s');", FlightDestination.getIdFlightDestination(), destination.getOriginCity(),destination.getDestinationCity(),destination.getFlightTime());
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Destination inserted","Congratulations",JOptionPane.PLAIN_MESSAGE);
        } catch (Exception var7) {
            JOptionPane.showMessageDialog(null,var7,"Error",JOptionPane.WARNING_MESSAGE);
        }
    }
    public void update_row_destination(Connection conn, FlightDestination destinationOld, FlightDestination destinationNew) {
        try {
            String query = String.format("update destinations set originCity='%s'\n" +
                                          "where originCity='%s' AND destinationCity='%s' AND flightTime='%s';\n" +
                                          "update destinations set destinationCity='%s'\n" +
                                            "where originCity='%s' AND destinationCity='%s' AND flightTime='%s';\n" +
                                           "update destinations set flightTime='%s'\n" +
                                           "where originCity='%s' AND destinationCity='%s' AND flightTime='%s';\n", destinationNew.getOriginCity(),destinationOld.getOriginCity(),destinationOld.getDestinationCity(),destinationOld.getFlightTime(),destinationNew.getDestinationCity(),destinationOld.getOriginCity(),destinationOld.getDestinationCity(),destinationOld.getFlightTime(),destinationNew.getFlightTime(),destinationOld.getOriginCity(),destinationOld.getDestinationCity(),destinationOld.getFlightTime());
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Destination updated","Congratulations",JOptionPane.PLAIN_MESSAGE);
        } catch (Exception var7) {
            JOptionPane.showMessageDialog(null,var7,"Error",JOptionPane.WARNING_MESSAGE);
        }
    }

    public void update_row_flight(Connection conn, Flight flightOld, Flight flightNew) {
        try {
            String query = String.format("update flights set idFlightDestination='%s'\n" +
                    "where idFlight='%s';\n" +
                    "update flights set flightDate='%s'\n" +
                    "where idFlight='%s';\n" +
                    "update flights set priceOfSeat='%s'\n" +
                    "where idFlight='%s';\n" +
                    "update flights set totalNumberOfSeats='%s'\n" +
                            "where idFlight='%s';\n", flightNew.getIdFlightDestination(),flightOld.getIdFlight(),flightNew.getFlightDate(),flightOld.getIdFlight(),flightNew.getPriceOfSeat(),flightOld.getIdFlight(),flightNew.getTotalNumberOfSeats(),flightOld.getIdFlight());
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Flight updated","Congratulations",JOptionPane.PLAIN_MESSAGE);
        } catch (Exception var7) {
            JOptionPane.showMessageDialog(null,var7,"Error",JOptionPane.WARNING_MESSAGE);
        }
    }

    public boolean insert_row_reservation(Connection conn, Reservation reservation) {
        boolean tryInsert=false;
        try {
            String query = String.format("insert into reservations(idReservation,numberOfTickets,idFlight,dateReservation,idUser,summary) values('%s','%s','%s','%s','%s','%s');", reservation.getPosition(), reservation.getNumberOfTickets(),reservation.getIdFlight(),reservation.getDateReservation(),reservation.getIdUser(),reservation.getTotalPrice());
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            tryInsert=true;
            System.out.println("Row reservation Inserted");
        } catch (Exception var7) {
            System.out.println(var7);
        }
        return tryInsert;
    }
    public boolean insert_row_payment(Connection conn, Payment payment) {
        boolean tryInsert=false;
        try {
            String query = String.format("insert into payments(idPayment,idReservation,dateOfPay,creditCardNumber) values('%s','%s','%s','%s');", payment.getPosition(), payment.getIdReservation(),payment.getDateOfPay(),payment.getCreditCardNumber());
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            tryInsert=true;
            System.out.println("Row payment Inserted");
        } catch (Exception var7) {
            System.out.println(var7);
        }
        return tryInsert;
    }
    public int maxId(Connection conn, String table_name, String id) {
        ResultSet rs = null;
        int retr=0;
        try {
            String query = String.format("select max(%s) from %s;",id, table_name);
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);

            while(rs.next()) {
                retr=Integer.parseInt(rs.getString("max"));
            }
        } catch (Exception var7) {
            System.out.println(var7);
        }
return retr;
    }
    public void delete_row_by_id_flight(Connection conn, String table_name, int id) {
        try {
            String query = String.format("delete from %s where idFlight= %s", table_name, id);
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Deleted");
        } catch (Exception var6) {
            System.out.println(var6);
        }
    }

    public void deleteReservation(Connection conn, Reservation reservation) {
        try {
            String query = String.format("delete from reservations where idReservation= '%s'",  reservation.getPosition());
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            //System.out.println("Data Deleted");
            JOptionPane.showMessageDialog(null, "Reservation deleted", "Congratulations", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception var6) {
            //System.out.println(var6);
            JOptionPane.showMessageDialog(null, var6, "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void deleteDestination(Connection conn, FlightDestination destination) {
        try {
            String query = String.format("delete from destinations where originCity= '%s' AND destinationCity= '%s'",  destination.getOriginCity(), destination.getDestinationCity());
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            //System.out.println("Data Deleted");
            JOptionPane.showMessageDialog(null, "Destination deleted", "Congratulations", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception var6) {
            //System.out.println(var6);
            JOptionPane.showMessageDialog(null, var6, "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public ArrayList<Reservation> searchBooking(Connection conn) {
        ResultSet rs = null;
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            String query = String.format("SELECT DISTINCT \n" +
                    "    r.idReservation, \n" +
                    "    r.numberOfTickets,\n" +
                    "    r.idFlight, \n" +
                    "    r.dateReservation, \n" +
                    "    r.idUser,\n" +
                    "    r.summary, \n" +
                    "    d.originCity, \n" +
                    "    d.destinationCity,\n" +
                    "    f.flightDate\n" +
                    "FROM \n" +
                    "    reservations r\n" +
                    "JOIN flights f ON r.idFlight = f.idFlight\n" +
                    "JOIN destinations d ON f.idFlightDestination = d.idDestination\n" +
                    "LEFT JOIN  payments p ON r.idReservation = p.idReservation\n" +
                    "WHERE \n" +
                    "    r.idUser = '%s'\n" +
                    "    AND r.dateReservation >= NOW() - INTERVAL '24 hours'\n" +
                    "    AND f.flightDate >= NOW() \n" +
                    "    AND p.idPayment IS NULL;", User.getIdUser());
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()) {
                reservations.add(new Reservation(rs.getInt("idReservation"),rs.getInt("numberOfTickets"),rs.getInt("idFlight"),rs.getString("dateReservation"),rs.getInt("idUser"),rs.getString("summary"),rs.getString("originCity"),rs.getString("destinationCity"),rs.getString("flightDate")));
            }
        } catch (Exception var7) {
            System.out.println(var7);
        }

        return reservations;
    }
    public ArrayList<FlightDestination> searchDestinations(Connection conn) {
        ResultSet rs = null;
        ArrayList<FlightDestination> destinations = new ArrayList<>();
        try {
            String query = String.format("SELECT * FROM destinations");
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()) {
                destinations.add(new FlightDestination(rs.getInt("idDestination"),rs.getString("originCity"),rs.getString("destinationCity"),rs.getString("flightTime")));
            }
        } catch (Exception var7) {
            System.out.println(var7);
        }
        return destinations;
    }
    public String[] readDestinations(Connection conn) {
        ResultSet rs = null;
        ArrayList<String> destinations = new ArrayList<>();
        try {
            String query = String.format("SELECT * FROM destinations");
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()) {
                destinations.add("           "+rs.getString("originCity")+"                   "+rs.getString("destinationCity"));
            }
        } catch (Exception var7) {
            System.out.println(var7);
        }
        String[] newDestinations = new String[destinations.size()];
        for(int i=0;i<newDestinations.length;i++){
            newDestinations[i]=destinations.get(i);
        }
        return newDestinations;
    }
    public ArrayList<Reservation> searchPayment(Connection conn) {
        ResultSet rs = null;
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            String query = String.format("SELECT DISTINCT r.idReservation, r.numberOfTickets,\n" +
                    "                r.idFlight, r.dateReservation, p.dateOfPay, r.idUser,\n" +
                    "                r.summary, d.originCity, d.destinationCity, f.flightDate\n" +
                    "FROM reservations r\n" +
                    "LEFT JOIN payments p ON r.idReservation = p.idReservation\n" +
                    "INNER JOIN flights f ON r.idFlight = f.idFlight\n" +
                    "INNER JOIN destinations d ON f.idFlightDestination = d.idDestination\n" +
                    "WHERE r.idUser = '%s' AND r.idReservation IN (SELECT idReservation FROM Payments);", User.getIdUser());
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);
            reservations.clear();
            while(rs.next()) {
                reservations.add(new Reservation(rs.getInt("idReservation"),rs.getInt("numberOfTickets"),rs.getInt("idFlight"),rs.getString("dateReservation"),rs.getInt("idUser"),rs.getString("summary"),rs.getString("originCity"),rs.getString("destinationCity"),rs.getString("flightDate"),rs.getString("dateOfPay")));
            }
        } catch (Exception var7) {
            System.out.println(var7);
        }
        return reservations;
    }

    public Flight search_flight(Connection conn, String originCity, String DestinationCity, String Date) {
        ResultSet rs = null;
        Flight flight = new Flight();
        try {
            String query = String.format("select idFlight, idFlightDestination,\n" +
                    "                     flightDate, originCity, destinationCity, flightTime, priceOfSeat,totalNumberOfSeats\n" +
                    "            from flights, destinations\n" +
                    "            where flights.idFlightDestination=destinations.idDestination AND\n" +
                    "            originCity LIKE '%s' AND destinationCity LIKE '%s' AND flightDate='%s'", originCity, DestinationCity,Date);


            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);

            while(rs.next()) {                             //int id, int idFlightDestination, String flightDate, double priceOfSeat
                flight = new Flight(rs.getInt("idFlight"),rs.getString("originCity"),rs.getString("destinationCity"),rs.getString("flightDate"),rs.getString("flightTime"),rs.getDouble("priceOfSeat"),rs.getInt("totalNumberOfSeats"));
                //int id, String originCity, String destinationCity, String flightDate, String flightTime, double priceOfSeat
            }
        } catch (Exception var7) {
            System.out.println(var7);
        }

        return flight;
    }
    public int searchFreeTickets(Connection conn, int idFlight) {
        ResultSet rs = null;
        int freeTickets=0;
        try {
            String query = String.format("SELECT \n" +
                    "    f.totalNumberOfSeats - \n" +
                    "    SUM(CASE \n" +
                    "            WHEN r.dateReservation >= NOW() - INTERVAL '24 hours' \n" +
                    "                 AND p.idPayment IS NULL \n" +
                    "            THEN r.numberOfTickets \n" +
                    "            ELSE 0 \n" +
                    "        END) -\n" +
                    "    SUM(CASE \n" +
                    "            WHEN p.idPayment IS NOT NULL \n" +
                    "            THEN r.numberOfTickets \n" +
                    "            ELSE 0 \n" +
                    "        END) AS availableSeats\n" +
                    "FROM  \n" +
                    "    flights f\n" +
                    "LEFT JOIN \n" +
                    "    reservations r ON f.idFlight = r.idFlight\n" +
                    "LEFT JOIN \n" +
                    "    payments p ON r.idReservation = p.idReservation\n" +
                    "WHERE \n" +
                    "    f.idFlight = '%s'\n" +
                    "GROUP BY \n" +
                    "    f.idFlight, f.totalNumberOfSeats;", idFlight);


            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);

            while(rs.next()) {
                freeTickets = rs.getInt("availableSeats");
            }
        } catch (Exception var7) {
            System.out.println(var7);
        }
        return freeTickets;
    }

    public void read_data_flight(Connection conn, String table_name) {
        System.out.println("Reading data from " + table_name);
        String query = String.format("SELECT * FROM %s", table_name);

        try {
            Statement statement = conn.createStatement();

            try {
                ResultSet rs = statement.executeQuery(query);

                try {
                    while(rs.next()) {
                        System.out.print(rs.getString("idFlight") + " ");
                        System.out.print(rs.getString("idFlightDestination") + " ");
                        System.out.print(rs.getString("flightDate") + " ");
                        System.out.println(rs.getString("priceOfSeat") + " ");
                    }
                } catch (Throwable var10) {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (Throwable var9) {
                            var10.addSuppressed(var9);
                        }
                    }

                    throw var10;
                }

                if (rs != null) {
                    rs.close();
                }
            } catch (Throwable var11) {
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (Throwable var8) {
                        var11.addSuppressed(var8);
                    }
                }
                throw var11;
            }
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException var12) {
            var12.printStackTrace();
        }
    }
    public void search_by_id_flight(Connection conn, String table_name, int id) {
        ResultSet rs = null;

        try {
            String query = String.format("select * from %s where idFlight= %s", table_name, id);
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);

            while(rs.next()) {
                System.out.print(rs.getString("idFlight") + " ");
                System.out.print(rs.getString("idFlightDestination") + " ");
                System.out.print(rs.getString("flightDate") + " ");
                System.out.println(rs.getString("priceOfSeat") + " ");
            }
        } catch (Exception var7) {
            System.out.println(var7);
        }
    }
    public ArrayList<Flight> search_by_dateAndCountry(Connection conn, String originCity, String DestinationCity, int NumberSeats, String Date) {
        ResultSet rs = null;
        ArrayList<Flight> flights = new ArrayList<>();
        try {
            String query = String.format("SELECT\n" +
                    "    f.idFlight,\n" +
                    "    d.originCity,\n" +
                    "    d.destinationCity,\n" +
                    "    f.flightDate,\n" +
                    "    d.flightTime,\n" +
                    "    f.priceOfSeat,\n" +
                    "    f.totalNumberOfSeats,\n" +
                    "    f.totalNumberOfSeats - SUM(CASE WHEN r.dateReservation >= NOW() - INTERVAL '24 hours' THEN r.numberOfTickets ELSE 0 END) AS availableSeats\n" +
                    "FROM \n" +
                    "    flights f\n" +
                    "JOIN \n" +
                    "    destinations d ON f.idFlightDestination = d.idDestination\n" +
                    "LEFT JOIN \n" +
                    "    reservations r ON f.idFlight = r.idFlight\n" +
                    "WHERE \n" +
                    "    d.originCity = '%s' AND\n" +
                    "    d.destinationCity = '%s' AND\n" +
                    "    DATE(f.flightDate) = '%s' AND \n" +
                    "    f.flightDate >= NOW() \n" +
                    "GROUP BY \n" +
                    "    f.idFlight, d.originCity, d.destinationCity, f.flightDate, d.flightTime, f.priceOfSeat, f.totalNumberOfSeats\n" +
                    "HAVING \n" +
                    "    f.totalNumberOfSeats - SUM(CASE WHEN r.dateReservation >= NOW() - INTERVAL '24 hours' THEN r.numberOfTickets ELSE 0 END) >= '%s';", originCity, DestinationCity,Date, NumberSeats);


            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);

            while(rs.next()) {
                flights.add(new Flight(rs.getInt("idFlight"),rs.getString("originCity"),rs.getString("destinationCity"),rs.getString("flightDate"),rs.getString("flightTime"),rs.getDouble("priceOfSeat"),rs.getInt("totalNumberOfSeats")));

            }
        } catch (Exception var7) {
            System.out.println(var7);
        }

        return flights;
    }
    public ArrayList<Flight> search_by_country(Connection conn, String originCity, String DestinationCity, int NumberSeats) {
        ResultSet rs = null;
        ArrayList<Flight> flights = new ArrayList<>();
        try {
            String query = String.format("SELECT\n" +
                    "    f.idFlight,\n" +
                            "    d.originCity,\n" +
                            "    d.destinationCity,\n" +
                            "    f.flightDate,\n" +
                            "    d.flightTime,\n" +
                            "    f.priceOfSeat,\n" +
                            "    f.totalNumberOfSeats,\n" +
                            "    f.totalNumberOfSeats - SUM(CASE WHEN r.dateReservation >= NOW() - INTERVAL '24 hours' THEN r.numberOfTickets ELSE 0 END) AS availableSeats\n" +
                            "FROM \n" +
                            "    flights f\n" +
                            "JOIN \n" +
                            "    destinations d ON f.idFlightDestination = d.idDestination\n" +
                            "LEFT JOIN \n" +
                            "    reservations r ON f.idFlight = r.idFlight\n" +
                            "WHERE \n" +
                            "    d.originCity = '%s' AND\n" +
                            "    d.destinationCity = '%s' AND\n" +
                            "    f.flightDate >= NOW() \n" +
                            "GROUP BY \n" +
                            "    f.idFlight, d.originCity, d.destinationCity, f.flightDate, d.flightTime, f.priceOfSeat, f.totalNumberOfSeats\n" +
                            "HAVING \n" +
                            "    f.totalNumberOfSeats - SUM(CASE WHEN r.dateReservation >= NOW() - INTERVAL '24 hours' THEN r.numberOfTickets ELSE 0 END) >= '%s';", originCity, DestinationCity,NumberSeats);


            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);

            while(rs.next()) {
                flights.add(new Flight(rs.getInt("idFlight"),rs.getString("originCity"),rs.getString("destinationCity"),rs.getString("flightDate"),rs.getString("flightTime"),rs.getDouble("priceOfSeat"),rs.getInt("totalNumberOfSeats")));
            }
        } catch (Exception var7) {
            System.out.println(var7);
        }
        return flights;
    }
    public ArrayList<Flight> search_by_date(Connection conn, String Date, int NumberSeats) {
        ResultSet rs = null;
        ArrayList<Flight> flights = new ArrayList<>();
        try {
            String query = String.format("SELECT\n" +
                    "    f.idFlight,\n" +
                            "    d.originCity,\n" +
                            "    d.destinationCity,\n" +
                            "    f.flightDate,\n" +
                            "    d.flightTime,\n" +
                            "    f.priceOfSeat,\n" +
                            "    f.totalNumberOfSeats,\n" +
                            "    f.totalNumberOfSeats - SUM(CASE WHEN r.dateReservation >= NOW() - INTERVAL '24 hours' THEN r.numberOfTickets ELSE 0 END) AS availableSeats\n" +
                            "FROM \n" +
                            "    flights f\n" +
                            "JOIN \n" +
                            "    destinations d ON f.idFlightDestination = d.idDestination\n" +
                            "LEFT JOIN \n" +
                            "    reservations r ON f.idFlight = r.idFlight\n" +
                            "WHERE \n" +
                            "    DATE(f.flightDate) = '%s' AND\n" +
                            "    f.flightDate >= NOW() \n" +
                            "GROUP BY \n" +
                            "    f.idFlight, d.originCity, d.destinationCity, f.flightDate, d.flightTime, f.priceOfSeat, f.totalNumberOfSeats\n" +
                            "HAVING \n" +
                            "    f.totalNumberOfSeats - SUM(CASE WHEN r.dateReservation >= NOW() - INTERVAL '24 hours' THEN r.numberOfTickets ELSE 0 END) >= '%s';",Date, NumberSeats);


            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);

            while(rs.next()) {
               flights.add(new Flight(rs.getInt("idFlight"),rs.getString("originCity"),rs.getString("destinationCity"),rs.getString("flightDate"),rs.getString("flightTime"),rs.getDouble("priceOfSeat"),rs.getInt("totalNumberOfSeats")));
            }
        } catch (Exception var7) {
            System.out.println(var7);
        }
        return flights;
    }

    public String[] readOriginCity(Connection conn) {
        String query = new String("SELECT distinct originCity FROM destinations");
        ArrayList<String> cityOrigen = new ArrayList<String>();

        try {
            Statement statement = conn.createStatement();

            try {
                ResultSet rs = statement.executeQuery(query);

                try {
                    while(rs.next()) {
                        cityOrigen.add(rs.getString("originCity"));
                    }
                } catch (Throwable var10) {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (Throwable var9) {
                            var10.addSuppressed(var9);
                        }
                    }

                    throw var10;
                }

                if (rs != null) {
                    rs.close();
                }
            } catch (Throwable var11) {
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (Throwable var8) {
                        var11.addSuppressed(var8);
                    }
                }
                throw var11;
            }
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException var12) {
            var12.printStackTrace();
        }
        String[] newOrigen = new String[cityOrigen.size()];
        for(int i=0;i<newOrigen.length;i++){
            newOrigen[i]=cityOrigen.get(i);
        }
        return newOrigen;
    }
    public String[] readDestinationCity(Connection conn) {
        String query = new String("SELECT distinct destinationCity FROM destinations");
        ArrayList<String> cityDestination = new ArrayList<String>();

        try {
            Statement statement = conn.createStatement();

            try {
                ResultSet rs = statement.executeQuery(query);

                try {
                    while(rs.next()) {
                        cityDestination.add(rs.getString("destinationCity"));
                    }
                } catch (Throwable var10) {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (Throwable var9) {
                            var10.addSuppressed(var9);
                        }
                    }

                    throw var10;
                }

                if (rs != null) {
                    rs.close();
                }
            } catch (Throwable var11) {
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (Throwable var8) {
                        var11.addSuppressed(var8);
                    }
                }
                throw var11;
            }
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException var12) {
            var12.printStackTrace();
        }
        String[] newcityDestination = new String[cityDestination.size()];
        for(int i=0;i<newcityDestination.length;i++){
            newcityDestination[i]=cityDestination.get(i);
        }
        return newcityDestination;
    }

    public void search_by_user(Connection conn, String name, String password) {
        ResultSet rs = null;
        System.out.println("Name: "+name);
        System.out.println("Password: "+password);
        try {
            String query = String.format("select * from users where nameuser LIKE '%s' and passworduser LIKE '%s'" , name, password);
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()) {
                User user = new User(rs.getInt("idUser"),rs.getString("passwordUser"),rs.getString("nameUser"),rs.getString("email"),rs.getString("phoneNumber"),rs.getBoolean("StatusManager"));
                User.setNameStaticUser(user.getNameUser());
            }
        } catch (Exception var7) {
            System.out.println(var7);
        }
    }
    public boolean search_by_user(Connection conn, String name) {
        ResultSet rs = null;
        Boolean check=false;
        try {
            String query = String.format("select * from users where nameUser LIKE '%s'" , name);
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()) {

                if(Objects.equals(rs.getString("nameUser"), name)) {
                    check=true;
                }

            }
        } catch (Exception var7) {
            System.out.println("error: "+var7);
        }

        return check;
    }

    public boolean searchDestination(Connection conn, String origenCity, String destinationCity) {
        ResultSet rs = null;
        Boolean check=false;
        try {
            String query = String.format("select * from destinations where originCity LIKE '%s' AND destinationCity LIKE '%s'" , origenCity,destinationCity);
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()) {
                if(Objects.equals(rs.getString("originCity"), origenCity)) {
                    check=true;
                    return check;
                }
            }
        } catch (Exception var7) {
            System.out.println(var7);
        }
        return check;
    }
    public void read_data(Connection conn, String table_name) {
        System.out.println("Reading data from " + table_name);
        String query = String.format("SELECT * FROM %s", table_name);

        try {
            Statement statement = conn.createStatement();

            try {
                ResultSet rs = statement.executeQuery(query);

                try {
                    while(rs.next()) {
                        System.out.print(rs.getString("empid") + " ");
                        System.out.print(rs.getString("name") + " ");
                        System.out.println(rs.getString("address") + " ");
                    }
                } catch (Throwable var10) {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (Throwable var9) {
                            var10.addSuppressed(var9);
                        }
                    }

                    throw var10;
                }

                if (rs != null) {
                    rs.close();
                }
            } catch (Throwable var11) {
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (Throwable var8) {
                        var11.addSuppressed(var8);
                    }
                }

                throw var11;
            }

            if (statement != null) {
                statement.close();
            }
        } catch (SQLException var12) {
            var12.printStackTrace();
        }

    }

    public void update_name(Connection conn, String table_name, String old_name, String new_name) {
        try {
            String query = String.format("update %s set name='%s' where name='%s'", table_name, new_name, old_name);
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Updated");
        } catch (Exception var7) {
            System.out.println(var7);
        }

    }

    public void search_by_name(Connection conn, String table_name, String name) {
        ResultSet rs = null;

        try {
            String query = String.format("select * from %s where name= '%s'", table_name, name);
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);

            while(rs.next()) {
                System.out.print(rs.getString("empid") + " ");
                System.out.print(rs.getString("name") + " ");
                System.out.println(rs.getString("address"));
            }
        } catch (Exception var7) {
            System.out.println(var7);
        }

    }

    public void search_by_id(Connection conn, String table_name, int id) {
        ResultSet rs = null;

        try {
            String query = String.format("select * from %s where empid= %s", table_name, id);
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);

            while(rs.next()) {
                System.out.print(rs.getString("empid") + " ");
                System.out.print(rs.getString("name") + " ");
                System.out.println(rs.getString("address"));
            }
        } catch (Exception var7) {
            System.out.println(var7);
        }

    }

    public void delete_row_by_name(Connection conn, String table_name, String name) {
        try {
            String query = String.format("delete from %s where name='%s'", table_name, name);
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Deleted");
        } catch (Exception var6) {
            System.out.println(var6);
        }

    }

    public void delete_row_by_id(Connection conn, String table_name, int id) {
        try {
            String query = String.format("delete from %s where empid= %s", table_name, id);
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Deleted");
        } catch (Exception var6) {
            System.out.println(var6);
        }

    }
    public void delete_table(Connection conn, String table_name) {
        try {
            String query = String.format("drop table %s", table_name);
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Deleted");
        } catch (Exception var5) {
            System.out.println(var5);
        }
    }
}
