package GUI;

import app.DbFunctions;
import app.models.Flight;
import app.models.Reservation;
import app.models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FrameBookingAndPayment extends JFrame implements ActionListener {
    JLabel labelSeatsOrigen, labelSeatsDest,labelOrigenName, labelDestinationName, labelNameTotalPrice,labelTotalAmount,labelNameRound, labelImage,labelDestinationOrigen,labelDestinationDest,labelOrigenCityOrigen, labelOrigenCityDest,labelFlightTimeOrigen,labelFlightTimeDest, labelDateOrigen,labelDateDest,  labelPriceOfSeatOrigen,labelPriceOfSeatDest, labelPriceOrigen,labelPriceDest,labelTotalPrice, labelName;

    JPanel panelImage, panelFlights, panelEnter,panelName, panelOrigen, panelRound, panelTotalPrice;
    JButton buttonBooking, buttonPayment, buttonBack;
    int idFlightOrigen, idFlightAround=0, numberOfTickets;
    String priceTotal, priceOrigen, priceDestination;
    DbFunctions db;
    Connection conn;

    public FrameBookingAndPayment(Connection conn, Flight flightToDestination, Flight flightAround, int numberOfSeats){
        db = new DbFunctions();
        this.conn = conn;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900,  700);
        this.setLayout(null);

        buttonBooking = new JButton("Booking");
        buttonBooking.setPreferredSize(new Dimension(120,40));
        buttonBooking.addActionListener(this);

        buttonPayment = new JButton("Payment");
        buttonPayment.setPreferredSize(new Dimension(120,40));
        buttonPayment.addActionListener(this);

        buttonBack = new JButton("Back");
        buttonBack.setPreferredSize(new Dimension(120,40));
        buttonBack.addActionListener(this);

        panelImage=new JPanel();
        panelFlights=new JPanel();
        panelEnter = new JPanel();
        panelName= new JPanel();
        panelOrigen= new JPanel();
        panelRound= new JPanel();
        panelTotalPrice= new JPanel();
        labelNameTotalPrice = new JLabel("Summary:   ");
        labelTotalAmount= new JLabel();

        labelImage = new JLabel();
        labelDateOrigen= new JLabel();
        labelOrigenCityOrigen= new JLabel();
        labelPriceOfSeatOrigen= new JLabel();
        labelDestinationOrigen= new JLabel();
        labelFlightTimeOrigen= new JLabel();
        labelPriceOrigen= new JLabel();
        labelTotalPrice= new JLabel();
        labelName= new JLabel();
        labelOrigenName=new JLabel();
        labelSeatsOrigen=new JLabel();
        labelSeatsDest=new JLabel();

        labelDateDest= new JLabel();
        labelOrigenCityDest= new JLabel();
        labelPriceOfSeatDest= new JLabel();
        labelDestinationDest= new JLabel();
        labelFlightTimeDest= new JLabel();
        labelPriceDest= new JLabel();
        labelTotalPrice= new JLabel();
        labelName= new JLabel();
        labelNameRound= new JLabel();
        labelDestinationName=new JLabel();

        labelName.setText("       Original airport     Airport of destination                        Date                                Time of flight           Price per seat        Total seats         Total amount ");
        labelNameRound.setText("       Original airport     Airport of destination                        Date                                Time of flight           Price per seat        Total seats         Total amount ");

        labelOrigenName.setText("Tickets to destination:");
        labelOrigenCityOrigen.setText(flightToDestination.getOriginCity());
        labelDestinationOrigen.setText(flightToDestination.getDestinationCity());
        labelFlightTimeOrigen.setText(flightToDestination.getFlightTime());
        labelDateOrigen.setText(flightToDestination.getFlightDate());
        labelPriceOfSeatOrigen.setText(String.valueOf(flightToDestination.getPriceOfSeat()));
        labelSeatsOrigen.setText(String.valueOf(numberOfSeats));


        idFlightOrigen=flightToDestination.getIdFlight();
        numberOfTickets=numberOfSeats;

        priceTotal = String.valueOf(numberOfSeats * flightToDestination.getPriceOfSeat());
        priceOrigen = String.valueOf(numberOfSeats * flightToDestination.getPriceOfSeat());

        if (flightAround!=null){
            idFlightAround=flightAround.getIdFlight();
            labelDestinationName.setText("Tickets back:");
            labelOrigenCityDest.setText(flightAround.getOriginCity());
            labelDestinationDest.setText(flightAround.getDestinationCity());
            labelFlightTimeDest.setText(flightAround.getFlightTime());
            labelDateDest.setText(flightAround.getFlightDate());
            labelPriceOfSeatDest.setText(String.valueOf(flightAround.getPriceOfSeat()));
            labelSeatsDest.setText(String.valueOf(numberOfSeats));


            priceDestination = String.valueOf(numberOfSeats * flightAround.getPriceOfSeat());
            priceTotal = String.valueOf(numberOfSeats * flightToDestination.getPriceOfSeat()+numberOfSeats * flightAround.getPriceOfSeat());
        }
        if (User.isIsManager()) {
            labelNameTotalPrice.setText("Summary with 50% discount: ");
            priceTotal= String.valueOf(numberOfSeats * flightToDestination.getPriceOfSeat()*0.5);
            priceOrigen = String.valueOf(numberOfSeats * flightToDestination.getPriceOfSeat()*0.5);

            if (flightAround!=null) {
                priceDestination = String.valueOf(numberOfSeats * flightAround.getPriceOfSeat()*0.5);
                priceTotal= String.valueOf((numberOfSeats * flightToDestination.getPriceOfSeat()+numberOfSeats * flightAround.getPriceOfSeat())*0.5);
            }
        }
        labelTotalAmount.setText(priceTotal);
        labelPriceOrigen.setText(priceOrigen);
        labelPriceDest.setText(priceDestination);

        labelOrigenCityOrigen.setPreferredSize(new Dimension(100,30));
        labelDestinationOrigen.setPreferredSize(new Dimension(100,30));
        labelFlightTimeOrigen.setPreferredSize(new Dimension(100,30));
        labelDateOrigen.setPreferredSize(new Dimension(200,30));
        labelPriceOfSeatOrigen.setPreferredSize(new Dimension(100,30));
        labelSeatsOrigen.setPreferredSize(new Dimension(100,30));
        labelSeatsDest.setPreferredSize(new Dimension(100,30));
        labelPriceOrigen.setPreferredSize(new Dimension(50,30));

        labelOrigenCityDest.setPreferredSize(new Dimension(100,30));
        labelDestinationDest.setPreferredSize(new Dimension(100,30));
        labelFlightTimeDest.setPreferredSize(new Dimension(100,30));
        labelDateDest.setPreferredSize(new Dimension(200,30));
        labelPriceOfSeatDest.setPreferredSize(new Dimension(100,30));
        labelPriceDest.setPreferredSize(new Dimension(50,30));
        labelTotalAmount.setPreferredSize(new Dimension(50,30));

        panelImage.setBounds(0,0, 900,300);
        labelOrigenName.setBounds(0,300, 900,50);

        labelName.setBounds(0,350, 900,30);
        panelOrigen.setBounds(0,380, 900,50);
        labelDestinationName.setBounds(0,430, 900,50);
        labelNameRound.setBounds(0,480, 900,30);
        panelRound.setBounds(0,510, 900,50);
        panelTotalPrice.setBounds(0,560, 900,50);
        panelEnter.setBounds(0,610, 900,50);


        panelImage.setBackground(Color.white);
        labelImage.setIcon(new ImageIcon("src/GUI/banner.jpg"));
        panelImage.add(labelImage);
        panelEnter.setBackground(Color.white);
        panelOrigen.setBackground(Color.white);
        panelRound.setBackground(Color.white);
        panelTotalPrice.setBackground(new Color(217, 214, 145));

        panelOrigen.setLayout(new FlowLayout());
        panelOrigen.add(labelOrigenCityOrigen);
        panelOrigen.add(labelDestinationOrigen);
        panelOrigen.add(labelDateOrigen);
        panelOrigen.add(labelFlightTimeOrigen);
        panelOrigen.add(labelPriceOfSeatOrigen);
        panelOrigen.add(labelSeatsOrigen);
        panelOrigen.add(labelPriceOrigen);

        panelRound.setLayout(new FlowLayout());
        panelRound.add(labelOrigenCityDest);
        panelRound.add(labelDestinationDest);
        panelRound.add(labelDateDest);
        panelRound.add(labelFlightTimeDest);
        panelRound.add(labelPriceOfSeatDest);
        panelRound.add(labelSeatsDest);
        panelRound.add(labelPriceDest);

        panelTotalPrice.add(labelNameTotalPrice);
        panelTotalPrice.add(labelTotalAmount);

        buttonBack.setBounds(730,620,120,30);
        buttonPayment.setBounds(600,620,120,30);
        buttonBooking.setBounds(470,620,120,30);

        panelEnter.add(buttonPayment);
        panelEnter.add(buttonBooking);
        panelEnter.add(buttonBack);

        this.add(panelImage);
        this.add(labelOrigenName);
        this.add(labelName);
        this.add(panelOrigen);
        this.add(labelDestinationName);
        if(flightAround!=null){
            this.add(labelNameRound);
            this.add(panelRound);
        }
        this.add(panelTotalPrice);
        this.add(panelEnter);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonBooking){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(new Date());
            if(db.insert_row_reservation(conn,new Reservation(numberOfTickets,idFlightOrigen, formattedDate,User.getIdUser(),priceOrigen))) {

                if (idFlightAround!=0) {
                    if(db.insert_row_reservation(conn,new Reservation(numberOfTickets,idFlightAround, formattedDate,User.getIdUser(),priceDestination))) {
                        JOptionPane.showMessageDialog(null,"You have successfully booked your round trip tickets. The reservation is valid for 24 hours, you can pay for it in your personal account","Congratulations",JOptionPane.PLAIN_MESSAGE);

                    }
                }else JOptionPane.showMessageDialog(null,"You have successfully booked your tickets. The reservation is valid for 24 hours, you can pay for it in your personal account","Congratulations",JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,"something went wrong... try again","Error",JOptionPane.WARNING_MESSAGE);
            }

            this.dispose();
        }
        if(e.getSource()==buttonPayment){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(new Date());
            Reservation reservationOrigen = new Reservation(numberOfTickets,idFlightOrigen, formattedDate,User.getIdUser(),priceOrigen,false);

            Reservation reservationAround = null;
            if (idFlightAround!=0) {

                reservationAround = new Reservation(numberOfTickets,idFlightAround, formattedDate,User.getIdUser(),priceDestination,false);
            }
            new FramePayment(conn,reservationOrigen,reservationAround,false);
            this.dispose();
        }

        if(e.getSource()==buttonBack){
            this.dispose();
        }
    }
}
