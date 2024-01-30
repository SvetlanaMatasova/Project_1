package app;

import app.models.Flight;
import app.models.FlightDestination;
import app.models.User;

import java.sql.Connection;
import java.io.FileWriter;
import java.io.IOException;
public class CreateTablesAndInsertRows {
    DbFunctions db;

    Connection conn;
    CreateTablesAndInsertRows(Connection conn){
        db =new DbFunctions();
        this.conn= conn;

        db.delete_table(conn, "flights,payments,reservations,users,destinations;");
        db.createTable(conn,"destinations","(\n" +
                "    idDestination int,\n" +
                "    originCity character(3) NOT NULL,\n" +
                "    destinationCity character(3) NOT NULL,\n" +
                "\tflightTime smallint,\n" +
                "    PRIMARY KEY (idDestination)\n" +
                ");");
        db.createTable(conn,"flights","(\n" +
                "    idFlight int,\n" +
                "\tidFlightDestination int, \n" +
                "    flightDate timestamp with time zone NOT NULL,\n" +
                "    priceOfSeat decimal(8,2) NOT NULL,\n" +
                "\ttotalNumberOfSeats int NOT NULL,\n" +
                "    \n" +
                "    PRIMARY KEY (idFlight),\n" +
                "\tFOREIGN KEY(idFlightDestination) REFERENCES destinations(idDestination)\n" +
                ");");
        db.createTable(conn,"users","(\n" +
                "    idUser int,\n" +
                "    nameUser character varying(20) UNIQUE NOT NULL,\n" +
                "\tpasswordUser character varying(20) NOT NULL,\n" +
                "    email character varying(20),\n" +
                "    phoneNumber character varying(20),\n" +
                "    StatusManager boolean,\n" +
                "    PRIMARY KEY (idUser)\n" +
                ");");
        db.createTable(conn,"reservations","(\n" +
                "    idReservation int,\n" +
                "    numberOfTickets smallint NOT NULL,\n" +
                "    idFlight int NOT NULL,\n" +
                "    dateReservation timestamp with time zone NOT NULL,\n" +
                "    idUser int NOT NULL,\n" +
                "\tsummary decimal(8,2) NOT NULL,\n" +
                "    PRIMARY KEY (idReservation),\n" +
                "\tFOREIGN KEY(idFlight) REFERENCES flights(idFlight),\n" +
                "\tFOREIGN KEY(idUser) REFERENCES users(idUser)\n" +
                ");");
        db.createTable(conn,"payments","(\n" +
                "    idPayment int,\n" +
                "    dateOfPay timestamp with time zone NOT NULL,\n" +
                "    idReservation int NOT NULL,\n" +
                "\tcreditCardNumber character varying(50),\n" +
                "    PRIMARY KEY (idPayment),\n" +
                "\tFOREIGN KEY(idReservation) REFERENCES reservations(idReservation)\n" +
                ");");

        db.insert_row_destinationToDB(conn, new FlightDestination("TLV",	"MXP",	"255"));
        db.insert_row_destinationToDB(conn, new FlightDestination("TLV",	"CDG",	"300"));
        db.insert_row_destinationToDB(conn, new FlightDestination("TLV",	"JFK",	"720"));
        db.insert_row_destinationToDB(conn, new FlightDestination("TLV",	"SVO",	"280"));
        db.insert_row_destinationToDB(conn, new FlightDestination("TLV",	"GRU",	"1045"));
        db.insert_row_destinationToDB(conn, new FlightDestination("TLV",	"SIN",	"740"));
        db.insert_row_destinationToDB(conn, new FlightDestination("TLV",	"DXB",	"195"));
        db.insert_row_destinationToDB(conn, new FlightDestination("TLV",	"HND",	"845"));
        db.insert_row_destinationToDB(conn, new FlightDestination("TLV",	"CAI",	"290"));
        db.insert_row_destinationToDB(conn, new FlightDestination("TLV",	"FCO",	"225"));
        db.insert_row_destinationToDB(conn, new FlightDestination("MXP",	"TLV",	"255"));
        db.insert_row_destinationToDB(conn, new FlightDestination("CDG",	"TLV",	"300"));
        db.insert_row_destinationToDB(conn, new FlightDestination("JFK",	"TLV",	"720"));
        db.insert_row_destinationToDB(conn, new FlightDestination("SVO",	"TLV",	"280"));
        db.insert_row_destinationToDB(conn, new FlightDestination("GRU",	"TLV",	"1045"));
        db.insert_row_destinationToDB(conn, new FlightDestination("SIN",	"TLV",	"740"));
        db.insert_row_destinationToDB(conn, new FlightDestination("DXB",	"TLV",	"195"));
        db.insert_row_destinationToDB(conn, new FlightDestination("HND",	"TLV",	"845"));
        db.insert_row_destinationToDB(conn, new FlightDestination("CAI",	"TLV",	"290"));
        db.insert_row_destinationToDB(conn, new FlightDestination("FCO",	"TLV",	"225"));



        //db.insert_row_destinationToDB(conn, new FlightDestination());


        db.insert_row_flightToDB(conn, new Flight(1,"28.01.2024 8:25",117,20));
        db.insert_row_flightToDB(conn, new Flight(1,"28.01.2024 20:25",118,20));
        db.insert_row_flightToDB(conn, new Flight(1,"29.01.2024 8:25",119,20));
        db.insert_row_flightToDB(conn, new Flight(1,"29.01.2024 20:25",120,20));
        db.insert_row_flightToDB(conn, new Flight(1,"30.01.2024 8:25",121,20));
        db.insert_row_flightToDB(conn, new Flight(1,"30.01.2024 20:25",122,20));
        db.insert_row_flightToDB(conn, new Flight(1,"31.01.2024 8:25",123,20));
        db.insert_row_flightToDB(conn, new Flight(1,"31.01.2024 20:25",124,20));
        db.insert_row_flightToDB(conn, new Flight(1,"01.02.2024 8:25",125,20));
        db.insert_row_flightToDB(conn, new Flight(1,"01.02.2024 20:25",126,20));
        db.insert_row_flightToDB(conn, new Flight(1,"02.02.2024 8:25",127,20));
        db.insert_row_flightToDB(conn, new Flight(1,"02.02.2024 20:25",128,20));
        db.insert_row_flightToDB(conn, new Flight(1,"03.02.2024 8:25",129,20));
        db.insert_row_flightToDB(conn, new Flight(1,"03.02.2024 20:25",130,20));
        db.insert_row_flightToDB(conn, new Flight(1,"04.02.2024 8:25",131,20));
        db.insert_row_flightToDB(conn, new Flight(1,"04.02.2024 20:25",132,20));
        db.insert_row_flightToDB(conn, new Flight(1,"05.02.2024 8:25",133,20));
        db.insert_row_flightToDB(conn, new Flight(1,"05.02.2024 20:25",134,20));
        db.insert_row_flightToDB(conn, new Flight(1,"06.02.2024 8:25",135,20));
        db.insert_row_flightToDB(conn, new Flight(1,"06.02.2024 20:25",136,20));
        db.insert_row_flightToDB(conn, new Flight(1,"07.02.2024 8:25",137,20));
        db.insert_row_flightToDB(conn, new Flight(1,"07.02.2024 20:25",138,20));
        db.insert_row_flightToDB(conn, new Flight(1,"08.02.2024 8:25",139,20));
        db.insert_row_flightToDB(conn, new Flight(1,"08.02.2024 20:25",140,20));
        db.insert_row_flightToDB(conn, new Flight(1,"09.02.2024 8:25",141,20));
        db.insert_row_flightToDB(conn, new Flight(1,"09.02.2024 20:25",142,20));
        db.insert_row_flightToDB(conn, new Flight(1,"10.02.2024 8:25",143,20));
        db.insert_row_flightToDB(conn, new Flight(1,"10.02.2024 20:25",144,20));


        db.insert_row_flightToDB(conn, new Flight(2,"28.01.2024 5:25",112,40));
        db.insert_row_flightToDB(conn, new Flight(2,"28.01.2024 16:25",113,40));
        db.insert_row_flightToDB(conn, new Flight(2,"29.01.2024 5:25",114,40));
        db.insert_row_flightToDB(conn, new Flight(2,"29.01.2024 16:25",115,40));
        db.insert_row_flightToDB(conn, new Flight(2,"30.01.2024 5:25",116,40));
        db.insert_row_flightToDB(conn, new Flight(2,"30.01.2024 16:25",117,40));
        db.insert_row_flightToDB(conn, new Flight(2,"31.01.2024 5:25",118,40));
        db.insert_row_flightToDB(conn, new Flight(2,"31.01.2024 16:25",119,40));
        db.insert_row_flightToDB(conn, new Flight(2,"01.02.2024 5:25",120,40));
        db.insert_row_flightToDB(conn, new Flight(2,"01.02.2024 16:25",121,40));
        db.insert_row_flightToDB(conn, new Flight(2,"02.02.2024 5:25",122,40));
        db.insert_row_flightToDB(conn, new Flight(2,"02.02.2024 16:25",123,40));
        db.insert_row_flightToDB(conn, new Flight(2,"03.02.2024 5:25",124,40));
        db.insert_row_flightToDB(conn, new Flight(2,"03.02.2024 16:25",125,40));
        db.insert_row_flightToDB(conn, new Flight(2,"04.02.2024 5:25",126,40));
        db.insert_row_flightToDB(conn, new Flight(2,"04.02.2024 16:25",127,40));
        db.insert_row_flightToDB(conn, new Flight(2,"05.02.2024 5:25",128,40));
        db.insert_row_flightToDB(conn, new Flight(2,"05.02.2024 16:25",129,40));
        db.insert_row_flightToDB(conn, new Flight(2,"06.02.2024 5:25",130,40));
        db.insert_row_flightToDB(conn, new Flight(2,"06.02.2024 20:25",131,40));
        db.insert_row_flightToDB(conn, new Flight(2,"07.02.2024 5:25",132,40));
        db.insert_row_flightToDB(conn, new Flight(2,"07.02.2024 20:25",133,40));
        db.insert_row_flightToDB(conn, new Flight(2,"08.02.2024 5:25",134,40));
        db.insert_row_flightToDB(conn, new Flight(2,"08.02.2024 20:25",135,40));
        db.insert_row_flightToDB(conn, new Flight(2,"09.02.2024 5:25",136,40));
        db.insert_row_flightToDB(conn, new Flight(2,"09.02.2024 20:25",137,40));
        db.insert_row_flightToDB(conn, new Flight(2,"10.02.2024 5:25",138,40));
        db.insert_row_flightToDB(conn, new Flight(2,"10.02.2024 20:25",139,40));


        db.insert_row_flightToDB(conn, new Flight(3,"28.01.2024 5:25",280,10));
        db.insert_row_flightToDB(conn, new Flight(3,"28.01.2024 16:25",281,10));
        db.insert_row_flightToDB(conn, new Flight(3,"29.01.2024 5:25",282,10));
        db.insert_row_flightToDB(conn, new Flight(3,"29.01.2024 16:25",283,10));
        db.insert_row_flightToDB(conn, new Flight(3,"30.01.2024 5:25",284,10));
        db.insert_row_flightToDB(conn, new Flight(3,"30.01.2024 16:25",285,10));
        db.insert_row_flightToDB(conn, new Flight(3,"31.01.2024 5:25",286,10));
        db.insert_row_flightToDB(conn, new Flight(3,"31.01.2024 16:25",287,10));
        db.insert_row_flightToDB(conn, new Flight(3,"01.02.2024 5:25",288,10));
        db.insert_row_flightToDB(conn, new Flight(3,"01.02.2024 16:25",289,10));
        db.insert_row_flightToDB(conn, new Flight(3,"02.02.2024 5:25",290,10));
        db.insert_row_flightToDB(conn, new Flight(3,"02.02.2024 16:25",291,10));
        db.insert_row_flightToDB(conn, new Flight(3,"03.02.2024 5:25",292,10));
        db.insert_row_flightToDB(conn, new Flight(3,"03.02.2024 16:25",293,10));
        db.insert_row_flightToDB(conn, new Flight(3,"04.02.2024 5:25",294,10));
        db.insert_row_flightToDB(conn, new Flight(3,"04.02.2024 16:25",295,10));
        db.insert_row_flightToDB(conn, new Flight(3,"05.02.2024 5:25",296,10));
        db.insert_row_flightToDB(conn, new Flight(3,"05.02.2024 16:25",297,10));
        db.insert_row_flightToDB(conn, new Flight(3,"06.02.2024 5:25",298,10));
        db.insert_row_flightToDB(conn, new Flight(3,"06.02.2024 20:25",299,10));
        db.insert_row_flightToDB(conn, new Flight(3,"07.02.2024 5:25",300,10));
        db.insert_row_flightToDB(conn, new Flight(3,"07.02.2024 20:25",301,10));
        db.insert_row_flightToDB(conn, new Flight(3,"08.02.2024 5:25",302,10));
        db.insert_row_flightToDB(conn, new Flight(3,"08.02.2024 20:25",303,10));
        db.insert_row_flightToDB(conn, new Flight(3,"09.02.2024 5:25",304,10));
        db.insert_row_flightToDB(conn, new Flight(3,"09.02.2024 20:25",305,10));
        db.insert_row_flightToDB(conn, new Flight(3,"10.02.2024 5:25",306,10));
        db.insert_row_flightToDB(conn, new Flight(3,"10.02.2024 20:25",307,10));


        db.insert_row_flightToDB(conn, new Flight(4,"28.01.2024 5:25",104,15));
        db.insert_row_flightToDB(conn, new Flight(4,"28.01.2024 16:25",105,15));
        db.insert_row_flightToDB(conn, new Flight(4,"29.01.2024 5:25",106,15));
        db.insert_row_flightToDB(conn, new Flight(4,"29.01.2024 16:25",107,15));
        db.insert_row_flightToDB(conn, new Flight(4,"30.01.2024 5:25",108,15));
        db.insert_row_flightToDB(conn, new Flight(4,"30.01.2024 16:25",109,15));
        db.insert_row_flightToDB(conn, new Flight(4,"31.01.2024 5:25",110,15));
        db.insert_row_flightToDB(conn, new Flight(4,"31.01.2024 16:25",111,15));
        db.insert_row_flightToDB(conn, new Flight(4,"01.02.2024 5:25",112,15));
        db.insert_row_flightToDB(conn, new Flight(4,"01.02.2024 16:25",113,15));
        db.insert_row_flightToDB(conn, new Flight(4,"02.02.2024 5:25",114,15));
        db.insert_row_flightToDB(conn, new Flight(4,"02.02.2024 16:25",115,15));
        db.insert_row_flightToDB(conn, new Flight(4,"03.02.2024 5:25",116,15));
        db.insert_row_flightToDB(conn, new Flight(4,"03.02.2024 16:25",117,15));
        db.insert_row_flightToDB(conn, new Flight(4,"04.02.2024 5:25",118,15));
        db.insert_row_flightToDB(conn, new Flight(4,"04.02.2024 16:25",119,15));
        db.insert_row_flightToDB(conn, new Flight(4,"05.02.2024 5:25",120,15));
        db.insert_row_flightToDB(conn, new Flight(4,"05.02.2024 16:25",121,15));
        db.insert_row_flightToDB(conn, new Flight(4,"06.02.2024 5:25",122,15));
        db.insert_row_flightToDB(conn, new Flight(4,"06.02.2024 20:25",123,15));
        db.insert_row_flightToDB(conn, new Flight(4,"07.02.2024 5:25",124,15));
        db.insert_row_flightToDB(conn, new Flight(4,"07.02.2024 20:25",125,15));
        db.insert_row_flightToDB(conn, new Flight(4,"08.02.2024 5:25",126,15));
        db.insert_row_flightToDB(conn, new Flight(4,"08.02.2024 20:25",127,15));
        db.insert_row_flightToDB(conn, new Flight(4,"09.02.2024 5:25",128,15));
        db.insert_row_flightToDB(conn, new Flight(4,"09.02.2024 20:25",129,15));
        db.insert_row_flightToDB(conn, new Flight(4,"10.02.2024 5:25",130,15));
        db.insert_row_flightToDB(conn, new Flight(4,"10.02.2024 20:25",131,15));

        db.insert_row_flightToDB(conn, new Flight(5,"28.01.2024 5:25",410,35));
        db.insert_row_flightToDB(conn, new Flight(5,"28.01.2024 16:25",411,35));
        db.insert_row_flightToDB(conn, new Flight(5,"29.01.2024 5:25",412,35));
        db.insert_row_flightToDB(conn, new Flight(5,"29.01.2024 16:25",413,35));
        db.insert_row_flightToDB(conn, new Flight(5,"30.01.2024 5:25",414,35));
        db.insert_row_flightToDB(conn, new Flight(5,"30.01.2024 16:25",415,35));
        db.insert_row_flightToDB(conn, new Flight(5,"31.01.2024 5:25",416,35));
        db.insert_row_flightToDB(conn, new Flight(5,"31.01.2024 16:25",417,35));
        db.insert_row_flightToDB(conn, new Flight(5,"01.02.2024 5:25",418,35));
        db.insert_row_flightToDB(conn, new Flight(5,"01.02.2024 16:25",419,35));
        db.insert_row_flightToDB(conn, new Flight(5,"02.02.2024 5:25",420,35));
        db.insert_row_flightToDB(conn, new Flight(5,"02.02.2024 16:25",421,35));
        db.insert_row_flightToDB(conn, new Flight(5,"03.02.2024 5:25",422,35));
        db.insert_row_flightToDB(conn, new Flight(5,"03.02.2024 16:25",423,35));
        db.insert_row_flightToDB(conn, new Flight(5,"04.02.2024 5:25",424,35));
        db.insert_row_flightToDB(conn, new Flight(5,"04.02.2024 16:25",425,35));
        db.insert_row_flightToDB(conn, new Flight(5,"05.02.2024 5:25",426,35));
        db.insert_row_flightToDB(conn, new Flight(5,"05.02.2024 16:25",427,35));
        db.insert_row_flightToDB(conn, new Flight(5,"06.02.2024 5:25",428,35));
        db.insert_row_flightToDB(conn, new Flight(5,"06.02.2024 20:25",429,35));
        db.insert_row_flightToDB(conn, new Flight(5,"07.02.2024 5:25",430,35));
        db.insert_row_flightToDB(conn, new Flight(5,"07.02.2024 20:25",431,35));
        db.insert_row_flightToDB(conn, new Flight(5,"08.02.2024 5:25",432,35));
        db.insert_row_flightToDB(conn, new Flight(5,"08.02.2024 20:25",433,35));
        db.insert_row_flightToDB(conn, new Flight(5,"09.02.2024 5:25",434,35));
        db.insert_row_flightToDB(conn, new Flight(5,"09.02.2024 20:25",435,35));
        db.insert_row_flightToDB(conn, new Flight(5,"10.02.2024 5:25",436,35));
        db.insert_row_flightToDB(conn, new Flight(5,"10.02.2024 20:25",437,35));


        db.insert_row_flightToDB(conn, new Flight(6,"28.01.2024 5:25",288,15));
        db.insert_row_flightToDB(conn, new Flight(6,"28.01.2024 16:25",289,15));
        db.insert_row_flightToDB(conn, new Flight(6,"29.01.2024 5:25",290,15));
        db.insert_row_flightToDB(conn, new Flight(6,"29.01.2024 16:25",291,15));
        db.insert_row_flightToDB(conn, new Flight(6,"30.01.2024 5:25",292,15));
        db.insert_row_flightToDB(conn, new Flight(6,"30.01.2024 16:25",293,15));
        db.insert_row_flightToDB(conn, new Flight(6,"31.01.2024 5:25",294,15));
        db.insert_row_flightToDB(conn, new Flight(6,"31.01.2024 16:25",295,15));
        db.insert_row_flightToDB(conn, new Flight(6,"01.02.2024 5:25",296,15));
        db.insert_row_flightToDB(conn, new Flight(6,"01.02.2024 16:25",297,15));
        db.insert_row_flightToDB(conn, new Flight(6,"02.02.2024 5:25",298,15));
        db.insert_row_flightToDB(conn, new Flight(6,"02.02.2024 16:25",299,15));
        db.insert_row_flightToDB(conn, new Flight(6,"03.02.2024 5:25",300,18));
        db.insert_row_flightToDB(conn, new Flight(6,"03.02.2024 16:25",301,18));
        db.insert_row_flightToDB(conn, new Flight(6,"04.02.2024 5:25",302,18));
        db.insert_row_flightToDB(conn, new Flight(6,"04.02.2024 16:25",303,18));
        db.insert_row_flightToDB(conn, new Flight(6,"05.02.2024 5:25",304,18));
        db.insert_row_flightToDB(conn, new Flight(6,"05.02.2024 16:25",305,18));
        db.insert_row_flightToDB(conn, new Flight(6,"06.02.2024 5:25",306,18));
        db.insert_row_flightToDB(conn, new Flight(6,"06.02.2024 20:25",307,18));
        db.insert_row_flightToDB(conn, new Flight(6,"07.02.2024 5:25",308,18));
        db.insert_row_flightToDB(conn, new Flight(6,"07.02.2024 20:25",309,18));
        db.insert_row_flightToDB(conn, new Flight(6,"08.02.2024 5:25",310,18));
        db.insert_row_flightToDB(conn, new Flight(6,"08.02.2024 20:25",311,18));
        db.insert_row_flightToDB(conn, new Flight(6,"09.02.2024 5:25",312,18));
        db.insert_row_flightToDB(conn, new Flight(6,"09.02.2024 20:25",313,18));
        db.insert_row_flightToDB(conn, new Flight(6,"10.02.2024 5:25",314,18));
        db.insert_row_flightToDB(conn, new Flight(6,"10.02.2024 20:25",315,18));


        db.insert_row_flightToDB(conn, new Flight(7,"28.01.2024 5:25",70,52));
        db.insert_row_flightToDB(conn, new Flight(7,"28.01.2024 16:25",71,52));
        db.insert_row_flightToDB(conn, new Flight(7,"29.01.2024 5:25",72,52));
        db.insert_row_flightToDB(conn, new Flight(7,"29.01.2024 16:25",73,52));
        db.insert_row_flightToDB(conn, new Flight(7,"30.01.2024 5:25",74,52));
        db.insert_row_flightToDB(conn, new Flight(7,"30.01.2024 16:25",75,52));
        db.insert_row_flightToDB(conn, new Flight(7,"31.01.2024 5:25",76,52));
        db.insert_row_flightToDB(conn, new Flight(7,"31.01.2024 16:25",77,52));
        db.insert_row_flightToDB(conn, new Flight(7,"01.02.2024 5:25",78,52));
        db.insert_row_flightToDB(conn, new Flight(7,"01.02.2024 16:25",79,52));
        db.insert_row_flightToDB(conn, new Flight(7,"02.02.2024 5:25",80,52));
        db.insert_row_flightToDB(conn, new Flight(7,"02.02.2024 16:25",81,52));
        db.insert_row_flightToDB(conn, new Flight(7,"03.02.2024 5:25",82,52));
        db.insert_row_flightToDB(conn, new Flight(7,"03.02.2024 16:25",83,52));
        db.insert_row_flightToDB(conn, new Flight(7,"04.02.2024 5:25",84,52));
        db.insert_row_flightToDB(conn, new Flight(7,"04.02.2024 16:25",85,52));
        db.insert_row_flightToDB(conn, new Flight(7,"05.02.2024 5:25",86,52));
        db.insert_row_flightToDB(conn, new Flight(7,"05.02.2024 16:25",87,52));
        db.insert_row_flightToDB(conn, new Flight(7,"06.02.2024 5:25",88,52));
        db.insert_row_flightToDB(conn, new Flight(7,"06.02.2024 20:25",89,52));
        db.insert_row_flightToDB(conn, new Flight(7,"07.02.2024 5:25",90,52));
        db.insert_row_flightToDB(conn, new Flight(7,"07.02.2024 20:25",91,52));
        db.insert_row_flightToDB(conn, new Flight(7,"08.02.2024 5:25",92,52));
        db.insert_row_flightToDB(conn, new Flight(7,"08.02.2024 20:25",93,52));
        db.insert_row_flightToDB(conn, new Flight(7,"09.02.2024 5:25",94,52));
        db.insert_row_flightToDB(conn, new Flight(7,"09.02.2024 20:25",95,52));
        db.insert_row_flightToDB(conn, new Flight(7,"10.02.2024 5:25",96,52));
        db.insert_row_flightToDB(conn, new Flight(7,"10.02.2024 20:25",97,52));

        db.insert_row_flightToDB(conn, new Flight(8,"28.01.2024 5:25",330,61));
        db.insert_row_flightToDB(conn, new Flight(8,"28.01.2024 16:25",331,61));
        db.insert_row_flightToDB(conn, new Flight(8,"29.01.2024 5:25",332,61));
        db.insert_row_flightToDB(conn, new Flight(8,"29.01.2024 16:25",333,61));
        db.insert_row_flightToDB(conn, new Flight(8,"30.01.2024 5:25",334,61));
        db.insert_row_flightToDB(conn, new Flight(8,"30.01.2024 16:25",335,61));
        db.insert_row_flightToDB(conn, new Flight(8,"31.01.2024 5:25",336,61));
        db.insert_row_flightToDB(conn, new Flight(8,"31.01.2024 16:25",337,61));
        db.insert_row_flightToDB(conn, new Flight(8,"01.02.2024 5:25",338,61));
        db.insert_row_flightToDB(conn, new Flight(8,"01.02.2024 16:25",339,61));
        db.insert_row_flightToDB(conn, new Flight(8,"02.02.2024 5:25",340,61));
        db.insert_row_flightToDB(conn, new Flight(8,"02.02.2024 16:25",341,61));
        db.insert_row_flightToDB(conn, new Flight(8,"03.02.2024 5:25",342,61));
        db.insert_row_flightToDB(conn, new Flight(8,"03.02.2024 16:25",343,61));
        db.insert_row_flightToDB(conn, new Flight(8,"04.02.2024 5:25",344,61));
        db.insert_row_flightToDB(conn, new Flight(8,"04.02.2024 16:25",345,61));
        db.insert_row_flightToDB(conn, new Flight(8,"05.02.2024 5:25",346,61));
        db.insert_row_flightToDB(conn, new Flight(8,"05.02.2024 16:25",347,61));
        db.insert_row_flightToDB(conn, new Flight(8,"06.02.2024 5:25",348,61));
        db.insert_row_flightToDB(conn, new Flight(8,"06.02.2024 20:25",349,61));
        db.insert_row_flightToDB(conn, new Flight(8,"07.02.2024 5:25",350,61));
        db.insert_row_flightToDB(conn, new Flight(8,"07.02.2024 20:25",351,61));
        db.insert_row_flightToDB(conn, new Flight(8,"08.02.2024 5:25",352,61));
        db.insert_row_flightToDB(conn, new Flight(8,"08.02.2024 20:25",353,61));
        db.insert_row_flightToDB(conn, new Flight(8,"09.02.2024 5:25",354,61));
        db.insert_row_flightToDB(conn, new Flight(8,"09.02.2024 20:25",355,61));
        db.insert_row_flightToDB(conn, new Flight(8,"10.02.2024 5:25",356,61));
        db.insert_row_flightToDB(conn, new Flight(8,"10.02.2024 20:25",357,61));

        db.insert_row_flightToDB(conn, new Flight(9,"28.01.2024 5:25",108,36));
        db.insert_row_flightToDB(conn, new Flight(9,"28.01.2024 16:25",109,36));
        db.insert_row_flightToDB(conn, new Flight(9,"29.01.2024 5:25",110,36));
        db.insert_row_flightToDB(conn, new Flight(9,"29.01.2024 16:25",111,36));
        db.insert_row_flightToDB(conn, new Flight(9,"30.01.2024 5:25",112,36));
        db.insert_row_flightToDB(conn, new Flight(9,"30.01.2024 16:25",113,36));
        db.insert_row_flightToDB(conn, new Flight(9,"31.01.2024 5:25",114,36));
        db.insert_row_flightToDB(conn, new Flight(9,"31.01.2024 16:25",115,36));
        db.insert_row_flightToDB(conn, new Flight(9,"01.02.2024 5:25",116,36));
        db.insert_row_flightToDB(conn, new Flight(9,"01.02.2024 16:25",117,36));
        db.insert_row_flightToDB(conn, new Flight(9,"02.02.2024 5:25",118,36));
        db.insert_row_flightToDB(conn, new Flight(9,"02.02.2024 16:25",119,36));
        db.insert_row_flightToDB(conn, new Flight(9,"03.02.2024 5:25",120,36));
        db.insert_row_flightToDB(conn, new Flight(9,"03.02.2024 16:25",121,36));
        db.insert_row_flightToDB(conn, new Flight(9,"04.02.2024 5:25",122,36));
        db.insert_row_flightToDB(conn, new Flight(9,"04.02.2024 16:25",123,36));
        db.insert_row_flightToDB(conn, new Flight(9,"05.02.2024 5:25",124,36));
        db.insert_row_flightToDB(conn, new Flight(9,"05.02.2024 16:25",125,36));
        db.insert_row_flightToDB(conn, new Flight(9,"06.02.2024 5:25",126,36));
        db.insert_row_flightToDB(conn, new Flight(9,"06.02.2024 20:25",127,36));
        db.insert_row_flightToDB(conn, new Flight(9,"07.02.2024 5:25",128,36));
        db.insert_row_flightToDB(conn, new Flight(9,"07.02.2024 20:25",129,36));
        db.insert_row_flightToDB(conn, new Flight(9,"08.02.2024 5:25",130,36));
        db.insert_row_flightToDB(conn, new Flight(9,"08.02.2024 20:25",131,36));
        db.insert_row_flightToDB(conn, new Flight(9,"09.02.2024 5:25",132,36));
        db.insert_row_flightToDB(conn, new Flight(9,"09.02.2024 20:25",133,36));
        db.insert_row_flightToDB(conn, new Flight(9,"10.02.2024 5:25",134,36));
        db.insert_row_flightToDB(conn, new Flight(9,"10.02.2024 20:25",135,36));

        db.insert_row_flightToDB(conn, new Flight(10,"28.01.2024 5:25",82,13));
        db.insert_row_flightToDB(conn, new Flight(10,"28.01.2024 16:25",83,13));
        db.insert_row_flightToDB(conn, new Flight(10,"29.01.2024 5:25",84,13));
        db.insert_row_flightToDB(conn, new Flight(10,"29.01.2024 16:25",85,13));
        db.insert_row_flightToDB(conn, new Flight(10,"30.01.2024 5:25",86,13));
        db.insert_row_flightToDB(conn, new Flight(10,"30.01.2024 16:25",87,13));
        db.insert_row_flightToDB(conn, new Flight(10,"31.01.2024 5:25",88,13));
        db.insert_row_flightToDB(conn, new Flight(10,"31.01.2024 16:25",89,13));
        db.insert_row_flightToDB(conn, new Flight(10,"01.02.2024 5:25",90,13));
        db.insert_row_flightToDB(conn, new Flight(10,"01.02.2024 16:25",91,13));
        db.insert_row_flightToDB(conn, new Flight(10,"02.02.2024 5:25",92,13));
        db.insert_row_flightToDB(conn, new Flight(10,"02.02.2024 16:25",93,13));
        db.insert_row_flightToDB(conn, new Flight(10,"03.02.2024 5:25",94,13));
        db.insert_row_flightToDB(conn, new Flight(10,"03.02.2024 16:25",95,13));
        db.insert_row_flightToDB(conn, new Flight(10,"04.02.2024 5:25",96,13));
        db.insert_row_flightToDB(conn, new Flight(10,"04.02.2024 16:25",97,13));
        db.insert_row_flightToDB(conn, new Flight(10,"05.02.2024 5:25",98,13));
        db.insert_row_flightToDB(conn, new Flight(10,"05.02.2024 16:25",99,13));
        db.insert_row_flightToDB(conn, new Flight(10,"06.02.2024 5:25",100,13));
        db.insert_row_flightToDB(conn, new Flight(10,"06.02.2024 20:25",101,13));
        db.insert_row_flightToDB(conn, new Flight(10,"07.02.2024 5:25",102,13));
        db.insert_row_flightToDB(conn, new Flight(10,"07.02.2024 20:25",103,13));
        db.insert_row_flightToDB(conn, new Flight(10,"08.02.2024 5:25",104,13));
        db.insert_row_flightToDB(conn, new Flight(10,"08.02.2024 20:25",105,13));
        db.insert_row_flightToDB(conn, new Flight(10,"09.02.2024 5:25",106,13));
        db.insert_row_flightToDB(conn, new Flight(10,"09.02.2024 20:25",107,13));
        db.insert_row_flightToDB(conn, new Flight(10,"10.02.2024 5:25",108,13));
        db.insert_row_flightToDB(conn, new Flight(10,"10.02.2024 20:25",109,13));

        db.insert_row_flightToDB(conn, new Flight(11,"28.01.2024 5:25",142,27));
        db.insert_row_flightToDB(conn, new Flight(11,"28.01.2024 20:25",143,27));
        db.insert_row_flightToDB(conn, new Flight(11,"29.01.2024 5:25",144,27));
        db.insert_row_flightToDB(conn, new Flight(11,"29.01.2024 20:25",145,27));
        db.insert_row_flightToDB(conn, new Flight(11,"30.01.2024 5:25",146,27));
        db.insert_row_flightToDB(conn, new Flight(11,"30.01.2024 20:25",147,27));
        db.insert_row_flightToDB(conn, new Flight(11,"31.01.2024 5:25",148,27));
        db.insert_row_flightToDB(conn, new Flight(11,"31.01.2024 20:25",149,27));
        db.insert_row_flightToDB(conn, new Flight(11,"01.02.2024 5:25",150,27));
        db.insert_row_flightToDB(conn, new Flight(11,"01.02.2024 20:25",151,27));
        db.insert_row_flightToDB(conn, new Flight(11,"02.02.2024 5:25",152,27));
        db.insert_row_flightToDB(conn, new Flight(11,"02.02.2024 20:25",153,27));
        db.insert_row_flightToDB(conn, new Flight(11,"03.02.2024 5:25",154,27));
        db.insert_row_flightToDB(conn, new Flight(11,"03.02.2024 20:25",155,27));
        db.insert_row_flightToDB(conn, new Flight(11,"04.02.2024 5:25",156,27));
        db.insert_row_flightToDB(conn, new Flight(11,"04.02.2024 20:25",157,27));
        db.insert_row_flightToDB(conn, new Flight(11,"05.02.2024 5:25",158,27));
        db.insert_row_flightToDB(conn, new Flight(11,"05.02.2024 20:25",159,27));
        db.insert_row_flightToDB(conn, new Flight(11,"06.02.2024 5:25",160,27));
        db.insert_row_flightToDB(conn, new Flight(11,"06.02.2024 20:25",161,27));
        db.insert_row_flightToDB(conn, new Flight(11,"07.02.2024 5:25",162,27));
        db.insert_row_flightToDB(conn, new Flight(11,"07.02.2024 20:25",163,27));
        db.insert_row_flightToDB(conn, new Flight(11,"08.02.2024 5:25",164,27));
        db.insert_row_flightToDB(conn, new Flight(11,"08.02.2024 20:25",165,27));
        db.insert_row_flightToDB(conn, new Flight(11,"09.02.2024 5:25",166,27));
        db.insert_row_flightToDB(conn, new Flight(11,"09.02.2024 20:25",167,27));
        db.insert_row_flightToDB(conn, new Flight(11,"10.02.2024 5:25",168,27));
        db.insert_row_flightToDB(conn, new Flight(11,"10.02.2024 20:25",169,27));

        db.insert_row_flightToDB(conn, new Flight(12,"28.01.2024 5:25",172,53));
        db.insert_row_flightToDB(conn, new Flight(12,"28.01.2024 16:25",173,53));
        db.insert_row_flightToDB(conn, new Flight(12,"29.01.2024 5:25",174,53));
        db.insert_row_flightToDB(conn, new Flight(12,"29.01.2024 16:25",175,53));
        db.insert_row_flightToDB(conn, new Flight(12,"30.01.2024 5:25",176,53));
        db.insert_row_flightToDB(conn, new Flight(12,"30.01.2024 16:25",177,53));
        db.insert_row_flightToDB(conn, new Flight(12,"31.01.2024 5:25",178,53));
        db.insert_row_flightToDB(conn, new Flight(12,"31.01.2024 16:25",179,53));
        db.insert_row_flightToDB(conn, new Flight(12,"01.02.2024 5:25",180,53));
        db.insert_row_flightToDB(conn, new Flight(12,"01.02.2024 16:25",181,53));
        db.insert_row_flightToDB(conn, new Flight(12,"02.02.2024 5:25",182,53));
        db.insert_row_flightToDB(conn, new Flight(12,"02.02.2024 16:25",183,53));
        db.insert_row_flightToDB(conn, new Flight(12,"03.02.2024 5:25",184,53));
        db.insert_row_flightToDB(conn, new Flight(12,"03.02.2024 16:25",185,53));
        db.insert_row_flightToDB(conn, new Flight(12,"04.02.2024 5:25",186,53));
        db.insert_row_flightToDB(conn, new Flight(12,"04.02.2024 16:25",187,53));
        db.insert_row_flightToDB(conn, new Flight(12,"05.02.2024 5:25",188,53));
        db.insert_row_flightToDB(conn, new Flight(12,"05.02.2024 16:25",189,53));
        db.insert_row_flightToDB(conn, new Flight(12,"06.02.2024 5:25",190,53));
        db.insert_row_flightToDB(conn, new Flight(12,"06.02.2024 20:25",191,53));
        db.insert_row_flightToDB(conn, new Flight(12,"07.02.2024 5:25",192,53));
        db.insert_row_flightToDB(conn, new Flight(12,"07.02.2024 20:25",193,53));
        db.insert_row_flightToDB(conn, new Flight(12,"08.02.2024 5:25",194,53));
        db.insert_row_flightToDB(conn, new Flight(12,"08.02.2024 20:25",195,53));
        db.insert_row_flightToDB(conn, new Flight(12,"09.02.2024 5:25",196,53));
        db.insert_row_flightToDB(conn, new Flight(12,"09.02.2024 20:25",197,53));
        db.insert_row_flightToDB(conn, new Flight(12,"10.02.2024 5:25",198,53));
        db.insert_row_flightToDB(conn, new Flight(12,"10.02.2024 20:25",199,53));

        db.insert_row_flightToDB(conn, new Flight(13,"28.01.2024 5:25",296,44));
        db.insert_row_flightToDB(conn, new Flight(13,"28.01.2024 16:25",297,44));
        db.insert_row_flightToDB(conn, new Flight(13,"29.01.2024 5:25",298,44));
        db.insert_row_flightToDB(conn, new Flight(13,"29.01.2024 16:25",299,44));
        db.insert_row_flightToDB(conn, new Flight(13,"30.01.2024 5:25",300,44));
        db.insert_row_flightToDB(conn, new Flight(13,"30.01.2024 16:25",301,44));
        db.insert_row_flightToDB(conn, new Flight(13,"31.01.2024 5:25",302,44));
        db.insert_row_flightToDB(conn, new Flight(13,"31.01.2024 16:25",303,44));
        db.insert_row_flightToDB(conn, new Flight(13,"01.02.2024 5:25",304,44));
        db.insert_row_flightToDB(conn, new Flight(13,"01.02.2024 16:25",305,44));
        db.insert_row_flightToDB(conn, new Flight(13,"02.02.2024 5:25",306,44));
        db.insert_row_flightToDB(conn, new Flight(13,"02.02.2024 16:25",307,44));
        db.insert_row_flightToDB(conn, new Flight(13,"03.02.2024 5:25",308,44));
        db.insert_row_flightToDB(conn, new Flight(13,"03.02.2024 16:25",309,44));
        db.insert_row_flightToDB(conn, new Flight(13,"04.02.2024 5:25",310,44));
        db.insert_row_flightToDB(conn, new Flight(13,"04.02.2024 16:25",311,44));
        db.insert_row_flightToDB(conn, new Flight(13,"05.02.2024 5:25",312,44));
        db.insert_row_flightToDB(conn, new Flight(13,"05.02.2024 16:25",313,44));
        db.insert_row_flightToDB(conn, new Flight(13,"06.02.2024 5:25",314,44));
        db.insert_row_flightToDB(conn, new Flight(13,"06.02.2024 20:25",315,44));
        db.insert_row_flightToDB(conn, new Flight(13,"07.02.2024 5:25",316,44));
        db.insert_row_flightToDB(conn, new Flight(13,"07.02.2024 20:25",317,44));
        db.insert_row_flightToDB(conn, new Flight(13,"08.02.2024 5:25",318,44));
        db.insert_row_flightToDB(conn, new Flight(13,"08.02.2024 20:25",319,44));
        db.insert_row_flightToDB(conn, new Flight(13,"09.02.2024 5:25",320,44));
        db.insert_row_flightToDB(conn, new Flight(13,"09.02.2024 20:25",321,44));
        db.insert_row_flightToDB(conn, new Flight(13,"10.02.2024 5:25",322,44));
        db.insert_row_flightToDB(conn, new Flight(13,"10.02.2024 20:25",323,44));

        db.insert_row_flightToDB(conn, new Flight(14,"28.01.2024 5:25",37,37));
        db.insert_row_flightToDB(conn, new Flight(14,"28.01.2024 16:25",38,37));
        db.insert_row_flightToDB(conn, new Flight(14,"29.01.2024 5:25",39,37));
        db.insert_row_flightToDB(conn, new Flight(14,"29.01.2024 16:25",40,37));
        db.insert_row_flightToDB(conn, new Flight(14,"30.01.2024 5:25",41,37));
        db.insert_row_flightToDB(conn, new Flight(14,"30.01.2024 16:25",42,37));
        db.insert_row_flightToDB(conn, new Flight(14,"31.01.2024 5:25",43,37));
        db.insert_row_flightToDB(conn, new Flight(14,"31.01.2024 16:25",44,37));
        db.insert_row_flightToDB(conn, new Flight(14,"01.02.2024 5:25",45,37));
        db.insert_row_flightToDB(conn, new Flight(14,"01.02.2024 16:25",46,37));
        db.insert_row_flightToDB(conn, new Flight(14,"02.02.2024 5:25",47,37));
        db.insert_row_flightToDB(conn, new Flight(14,"02.02.2024 16:25",48,37));
        db.insert_row_flightToDB(conn, new Flight(14,"03.02.2024 5:25",49,37));
        db.insert_row_flightToDB(conn, new Flight(14,"03.02.2024 16:25",50,37));
        db.insert_row_flightToDB(conn, new Flight(14,"04.02.2024 5:25",51,37));
        db.insert_row_flightToDB(conn, new Flight(14,"04.02.2024 16:25",52,37));
        db.insert_row_flightToDB(conn, new Flight(14,"05.02.2024 5:25",53,37));
        db.insert_row_flightToDB(conn, new Flight(14,"05.02.2024 16:25",54,37));
        db.insert_row_flightToDB(conn, new Flight(14,"06.02.2024 5:25",55,37));
        db.insert_row_flightToDB(conn, new Flight(14,"06.02.2024 20:25",56,37));
        db.insert_row_flightToDB(conn, new Flight(14,"07.02.2024 5:25",57,37));
        db.insert_row_flightToDB(conn, new Flight(14,"07.02.2024 20:25",58,37));
        db.insert_row_flightToDB(conn, new Flight(14,"08.02.2024 5:25",59,37));
        db.insert_row_flightToDB(conn, new Flight(14,"08.02.2024 20:25",60,37));
        db.insert_row_flightToDB(conn, new Flight(14,"09.02.2024 5:25",61,37));
        db.insert_row_flightToDB(conn, new Flight(14,"09.02.2024 20:25",62,37));
        db.insert_row_flightToDB(conn, new Flight(14,"10.02.2024 5:25",63,37));
        db.insert_row_flightToDB(conn, new Flight(14,"10.02.2024 20:25",64,37));

        db.insert_row_flightToDB(conn, new Flight(15,"28.01.2024 5:25",449,63));
        db.insert_row_flightToDB(conn, new Flight(15,"28.01.2024 16:25",450,63));
        db.insert_row_flightToDB(conn, new Flight(15,"29.01.2024 5:25",451,63));
        db.insert_row_flightToDB(conn, new Flight(15,"29.01.2024 16:25",452,63));
        db.insert_row_flightToDB(conn, new Flight(15,"30.01.2024 5:25",453,63));
        db.insert_row_flightToDB(conn, new Flight(15,"30.01.2024 16:25",454,63));
        db.insert_row_flightToDB(conn, new Flight(15,"31.01.2024 5:25",455,63));
        db.insert_row_flightToDB(conn, new Flight(15,"31.01.2024 16:25",456,63));
        db.insert_row_flightToDB(conn, new Flight(15,"01.02.2024 5:25",457,63));
        db.insert_row_flightToDB(conn, new Flight(15,"01.02.2024 16:25",458,63));
        db.insert_row_flightToDB(conn, new Flight(15,"02.02.2024 5:25",459,63));
        db.insert_row_flightToDB(conn, new Flight(15,"02.02.2024 16:25",460,63));
        db.insert_row_flightToDB(conn, new Flight(15,"03.02.2024 5:25",461,63));
        db.insert_row_flightToDB(conn, new Flight(15,"03.02.2024 16:25",462,63));
        db.insert_row_flightToDB(conn, new Flight(15,"04.02.2024 5:25",463,63));
        db.insert_row_flightToDB(conn, new Flight(15,"04.02.2024 16:25",464,63));
        db.insert_row_flightToDB(conn, new Flight(15,"05.02.2024 5:25",465,63));
        db.insert_row_flightToDB(conn, new Flight(15,"05.02.2024 16:25",466,63));
        db.insert_row_flightToDB(conn, new Flight(15,"06.02.2024 5:25",467,63));
        db.insert_row_flightToDB(conn, new Flight(15,"06.02.2024 20:25",468,63));
        db.insert_row_flightToDB(conn, new Flight(15,"07.02.2024 5:25",469,63));
        db.insert_row_flightToDB(conn, new Flight(15,"07.02.2024 20:25",470,63));
        db.insert_row_flightToDB(conn, new Flight(15,"08.02.2024 5:25",471,63));
        db.insert_row_flightToDB(conn, new Flight(15,"08.02.2024 20:25",472,63));
        db.insert_row_flightToDB(conn, new Flight(15,"09.02.2024 5:25",473,63));
        db.insert_row_flightToDB(conn, new Flight(15,"09.02.2024 20:25",474,63));
        db.insert_row_flightToDB(conn, new Flight(15,"10.02.2024 5:25",475,63));
        db.insert_row_flightToDB(conn, new Flight(15,"10.02.2024 20:25",476,63));

        db.insert_row_flightToDB(conn, new Flight(16,"28.01.2024 5:25",208,14));
        db.insert_row_flightToDB(conn, new Flight(16,"28.01.2024 16:25",209,14));
        db.insert_row_flightToDB(conn, new Flight(16,"29.01.2024 5:25",210,14));
        db.insert_row_flightToDB(conn, new Flight(16,"29.01.2024 16:25",211,14));
        db.insert_row_flightToDB(conn, new Flight(16,"30.01.2024 5:25",212,14));
        db.insert_row_flightToDB(conn, new Flight(16,"30.01.2024 16:25",213,14));
        db.insert_row_flightToDB(conn, new Flight(16,"31.01.2024 5:25",214,14));
        db.insert_row_flightToDB(conn, new Flight(16,"31.01.2024 16:25",215,14));
        db.insert_row_flightToDB(conn, new Flight(16,"01.02.2024 5:25",216,14));
        db.insert_row_flightToDB(conn, new Flight(16,"01.02.2024 16:25",217,14));
        db.insert_row_flightToDB(conn, new Flight(16,"02.02.2024 5:25",218,14));
        db.insert_row_flightToDB(conn, new Flight(16,"02.02.2024 16:25",219,14));
        db.insert_row_flightToDB(conn, new Flight(16,"03.02.2024 5:25",220,14));
        db.insert_row_flightToDB(conn, new Flight(16,"03.02.2024 16:25",221,14));
        db.insert_row_flightToDB(conn, new Flight(16,"04.02.2024 5:25",222,14));
        db.insert_row_flightToDB(conn, new Flight(16,"04.02.2024 16:25",223,14));
        db.insert_row_flightToDB(conn, new Flight(16,"05.02.2024 5:25",224,14));
        db.insert_row_flightToDB(conn, new Flight(16,"05.02.2024 16:25",225,14));
        db.insert_row_flightToDB(conn, new Flight(16,"06.02.2024 5:25",226,14));
        db.insert_row_flightToDB(conn, new Flight(16,"06.02.2024 20:25",227,14));
        db.insert_row_flightToDB(conn, new Flight(16,"07.02.2024 5:25",228,14));
        db.insert_row_flightToDB(conn, new Flight(16,"07.02.2024 20:25",229,14));
        db.insert_row_flightToDB(conn, new Flight(16,"08.02.2024 5:25",230,14));
        db.insert_row_flightToDB(conn, new Flight(16,"08.02.2024 20:25",231,14));
        db.insert_row_flightToDB(conn, new Flight(16,"09.02.2024 5:25",232,14));
        db.insert_row_flightToDB(conn, new Flight(16,"09.02.2024 20:25",233,14));
        db.insert_row_flightToDB(conn, new Flight(16,"10.02.2024 5:25",234,14));
        db.insert_row_flightToDB(conn, new Flight(16,"10.02.2024 20:25",235,14));

        db.insert_row_flightToDB(conn, new Flight(17,"28.01.2024 5:25",155,46));
        db.insert_row_flightToDB(conn, new Flight(17,"28.01.2024 16:25",156,46));
        db.insert_row_flightToDB(conn, new Flight(17,"29.01.2024 5:25",157,46));
        db.insert_row_flightToDB(conn, new Flight(17,"29.01.2024 16:25",158,46));
        db.insert_row_flightToDB(conn, new Flight(17,"30.01.2024 5:25",159,46));
        db.insert_row_flightToDB(conn, new Flight(17,"30.01.2024 16:25",160,46));
        db.insert_row_flightToDB(conn, new Flight(17,"31.01.2024 5:25",161,46));
        db.insert_row_flightToDB(conn, new Flight(17,"31.01.2024 16:25",162,46));
        db.insert_row_flightToDB(conn, new Flight(17,"01.02.2024 5:25",163,46));
        db.insert_row_flightToDB(conn, new Flight(17,"01.02.2024 16:25",164,46));
        db.insert_row_flightToDB(conn, new Flight(17,"02.02.2024 5:25",165,46));
        db.insert_row_flightToDB(conn, new Flight(17,"02.02.2024 16:25",166,46));
        db.insert_row_flightToDB(conn, new Flight(17,"03.02.2024 5:25",167,46));
        db.insert_row_flightToDB(conn, new Flight(17,"03.02.2024 16:25",168,46));
        db.insert_row_flightToDB(conn, new Flight(17,"04.02.2024 5:25",169,46));
        db.insert_row_flightToDB(conn, new Flight(17,"04.02.2024 16:25",170,46));
        db.insert_row_flightToDB(conn, new Flight(17,"05.02.2024 5:25",171,46));
        db.insert_row_flightToDB(conn, new Flight(17,"05.02.2024 16:25",172,46));
        db.insert_row_flightToDB(conn, new Flight(17,"06.02.2024 5:25",173,46));
        db.insert_row_flightToDB(conn, new Flight(17,"06.02.2024 20:25",174,46));
        db.insert_row_flightToDB(conn, new Flight(17,"07.02.2024 5:25",175,46));
        db.insert_row_flightToDB(conn, new Flight(17,"07.02.2024 20:25",176,46));
        db.insert_row_flightToDB(conn, new Flight(17,"08.02.2024 5:25",177,46));
        db.insert_row_flightToDB(conn, new Flight(17,"08.02.2024 20:25",178,46));
        db.insert_row_flightToDB(conn, new Flight(17,"09.02.2024 5:25",179,46));
        db.insert_row_flightToDB(conn, new Flight(17,"09.02.2024 20:25",180,46));
        db.insert_row_flightToDB(conn, new Flight(17,"10.02.2024 5:25",181,46));
        db.insert_row_flightToDB(conn, new Flight(17,"10.02.2024 20:25",182,46));


        db.insert_row_flightToDB(conn, new Flight(18,"28.01.2024 5:25",235,73));
        db.insert_row_flightToDB(conn, new Flight(18,"28.01.2024 16:25",236,73));
        db.insert_row_flightToDB(conn, new Flight(18,"29.01.2024 5:25",237,73));
        db.insert_row_flightToDB(conn, new Flight(18,"29.01.2024 16:25",238,73));
        db.insert_row_flightToDB(conn, new Flight(18,"30.01.2024 5:25",239,73));
        db.insert_row_flightToDB(conn, new Flight(18,"30.01.2024 16:25",240,73));
        db.insert_row_flightToDB(conn, new Flight(18,"31.01.2024 5:25",241,73));
        db.insert_row_flightToDB(conn, new Flight(18,"31.01.2024 16:25",242,73));
        db.insert_row_flightToDB(conn, new Flight(18,"01.02.2024 5:25",243,73));
        db.insert_row_flightToDB(conn, new Flight(18,"01.02.2024 16:25",244,73));
        db.insert_row_flightToDB(conn, new Flight(18,"02.02.2024 5:25",245,73));
        db.insert_row_flightToDB(conn, new Flight(18,"02.02.2024 16:25",246,73));
        db.insert_row_flightToDB(conn, new Flight(18,"03.02.2024 5:25",247,73));
        db.insert_row_flightToDB(conn, new Flight(18,"03.02.2024 16:25",248,73));
        db.insert_row_flightToDB(conn, new Flight(18,"04.02.2024 5:25",249,73));
        db.insert_row_flightToDB(conn, new Flight(18,"04.02.2024 16:25",250,73));
        db.insert_row_flightToDB(conn, new Flight(18,"05.02.2024 5:25",251,73));
        db.insert_row_flightToDB(conn, new Flight(18,"05.02.2024 16:25",252,73));
        db.insert_row_flightToDB(conn, new Flight(18,"06.02.2024 5:25",253,73));
        db.insert_row_flightToDB(conn, new Flight(18,"06.02.2024 20:25",254,73));
        db.insert_row_flightToDB(conn, new Flight(18,"07.02.2024 5:25",255,73));
        db.insert_row_flightToDB(conn, new Flight(18,"07.02.2024 20:25",256,73));
        db.insert_row_flightToDB(conn, new Flight(18,"08.02.2024 5:25",257,73));
        db.insert_row_flightToDB(conn, new Flight(18,"08.02.2024 20:25",258,73));
        db.insert_row_flightToDB(conn, new Flight(18,"09.02.2024 5:25",259,73));
        db.insert_row_flightToDB(conn, new Flight(18,"09.02.2024 20:25",260,73));
        db.insert_row_flightToDB(conn, new Flight(18,"10.02.2024 5:25",261,73));
        db.insert_row_flightToDB(conn, new Flight(18,"10.02.2024 20:25",262,73));

        db.insert_row_flightToDB(conn, new Flight(19,"28.01.2024 5:25",129,64));
        db.insert_row_flightToDB(conn, new Flight(19,"28.01.2024 16:25",130,64));
        db.insert_row_flightToDB(conn, new Flight(19,"29.01.2024 5:25",131,64));
        db.insert_row_flightToDB(conn, new Flight(19,"29.01.2024 16:25",132,64));
        db.insert_row_flightToDB(conn, new Flight(19,"30.01.2024 5:25",133,64));
        db.insert_row_flightToDB(conn, new Flight(19,"30.01.2024 16:25",134,64));
        db.insert_row_flightToDB(conn, new Flight(19,"31.01.2024 5:25",135,64));
        db.insert_row_flightToDB(conn, new Flight(19,"31.01.2024 16:25",136,64));
        db.insert_row_flightToDB(conn, new Flight(19,"01.02.2024 5:25",137,64));
        db.insert_row_flightToDB(conn, new Flight(19,"01.02.2024 16:25",138,64));
        db.insert_row_flightToDB(conn, new Flight(19,"02.02.2024 5:25",139,64));
        db.insert_row_flightToDB(conn, new Flight(19,"02.02.2024 16:25",140,64));
        db.insert_row_flightToDB(conn, new Flight(19,"03.02.2024 5:25",141,64));
        db.insert_row_flightToDB(conn, new Flight(19,"03.02.2024 16:25",142,64));
        db.insert_row_flightToDB(conn, new Flight(19,"04.02.2024 5:25",143,64));
        db.insert_row_flightToDB(conn, new Flight(19,"04.02.2024 16:25",144,64));
        db.insert_row_flightToDB(conn, new Flight(19,"05.02.2024 5:25",145,64));
        db.insert_row_flightToDB(conn, new Flight(19,"05.02.2024 16:25",146,64));
        db.insert_row_flightToDB(conn, new Flight(19,"06.02.2024 5:25",147,64));
        db.insert_row_flightToDB(conn, new Flight(19,"06.02.2024 20:25",148,64));
        db.insert_row_flightToDB(conn, new Flight(19,"07.02.2024 5:25",149,64));
        db.insert_row_flightToDB(conn, new Flight(19,"07.02.2024 20:25",150,64));
        db.insert_row_flightToDB(conn, new Flight(19,"08.02.2024 5:25",151,64));
        db.insert_row_flightToDB(conn, new Flight(19,"08.02.2024 20:25",152,64));
        db.insert_row_flightToDB(conn, new Flight(19,"09.02.2024 5:25",153,64));
        db.insert_row_flightToDB(conn, new Flight(19,"09.02.2024 20:25",154,64));
        db.insert_row_flightToDB(conn, new Flight(19,"10.02.2024 5:25",155,64));
        db.insert_row_flightToDB(conn, new Flight(19,"10.02.2024 20:25",156,64));

        db.insert_row_flightToDB(conn, new Flight(20,"28.01.2024 5:25",47,82));
        db.insert_row_flightToDB(conn, new Flight(20,"28.01.2024 5:25",48,82));
        db.insert_row_flightToDB(conn, new Flight(20,"29.01.2024 5:25",49,82));
        db.insert_row_flightToDB(conn, new Flight(20,"29.01.2024 5:25",50,82));
        db.insert_row_flightToDB(conn, new Flight(20,"30.01.2024 5:25",51,82));
        db.insert_row_flightToDB(conn, new Flight(20,"30.01.2024 5:25",52,82));
        db.insert_row_flightToDB(conn, new Flight(20,"31.01.2024 5:25",53,82));
        db.insert_row_flightToDB(conn, new Flight(20,"31.01.2024 5:25",54,82));
        db.insert_row_flightToDB(conn, new Flight(20,"01.02.2024 5:25",55,82));
        db.insert_row_flightToDB(conn, new Flight(20,"01.02.2024 16:25",56,82));
        db.insert_row_flightToDB(conn, new Flight(20,"02.02.2024 5:25",57,82));
        db.insert_row_flightToDB(conn, new Flight(20,"02.02.2024 16:25",58,82));
        db.insert_row_flightToDB(conn, new Flight(20,"03.02.2024 5:25",59,82));
        db.insert_row_flightToDB(conn, new Flight(20,"03.02.2024 16:25",60,82));
        db.insert_row_flightToDB(conn, new Flight(20,"04.02.2024 5:25",61,82));
        db.insert_row_flightToDB(conn, new Flight(20,"04.02.2024 16:25",62,82));
        db.insert_row_flightToDB(conn, new Flight(20,"05.02.2024 5:25",63,82));
        db.insert_row_flightToDB(conn, new Flight(20,"05.02.2024 16:25",64,82));
        db.insert_row_flightToDB(conn, new Flight(20,"06.02.2024 5:25",65,82));
        db.insert_row_flightToDB(conn, new Flight(20,"06.02.2024 20:25",66,82));
        db.insert_row_flightToDB(conn, new Flight(20,"07.02.2024 5:25",67,82));
        db.insert_row_flightToDB(conn, new Flight(20,"07.02.2024 20:25",68,82));
        db.insert_row_flightToDB(conn, new Flight(20,"08.02.2024 5:25",69,82));
        db.insert_row_flightToDB(conn, new Flight(20,"08.02.2024 20:25",70,82));
        db.insert_row_flightToDB(conn, new Flight(20,"09.02.2024 5:25",71,82));
        db.insert_row_flightToDB(conn, new Flight(20,"09.02.2024 20:25",72,82));
        db.insert_row_flightToDB(conn, new Flight(20,"10.02.2024 5:25",73,82));
        db.insert_row_flightToDB(conn, new Flight(20,"10.02.2024 20:25",74,82));

        //db.insert_row_flight(conn, new Flight());

        db.insert_row_userToDB(conn, new User("Masha","Masha@gmail.com"	,"89724536582"	,"masha123"	,	false));
        db.insert_row_userToDB(conn, new User("Ron","Ron@gmail.com"	,"89724158732"	,"agk5vcw3"	,	false));
        db.insert_row_userToDB(conn, new User("tom34","tom34@gmail.com"	,"89724536582"	,"tom34iscool"	,	true));
        db.insert_row_userToDB(conn, new User("ilon","ilon@gmail.com"	,"89724536582"	,"1q2w3e"	,	true));
        db.insert_row_userToDB(conn, new User("bob1990","bob1990@gmail.com"	,"89724536582"	,"12345"	,	false));
        db.insert_row_userToDB(conn, new User("bagel","bagel@gmail.com"	,"89724536582"	,"bagel123"	,	true));

        User.setStatusUser(false);
        User.setIsManager(false);

        try {
            FileWriter writer = new FileWriter("src/keys.txt");
            writer.write("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
