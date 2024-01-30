package app;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import GUI.FrameAddFlight;
import GUI.MyFrame;
import app.models.*;

import java.sql.Connection;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {

        DbFunctions db = new DbFunctions();
        Connection conn = db.connect_to_db("Project", "postgres", "12345");

        new CreateTablesAndInsertRows(conn);

        //change static id
        Flight.setIdFlight(db.maxId(conn, "flights", "idFlight"));
        FlightDestination.setIdFlightDestination(db.maxId(conn, "destinations", "idDestination"));
        Payment.setIdPayment(db.maxId(conn, "payments", "idPayment"));
        Reservation.setIdReservation(db.maxId(conn, "reservations", "idReservation"));
        User.setIdUser(db.maxId(conn, "users", "idUser"));

        new MyFrame(conn);
    }
    }






