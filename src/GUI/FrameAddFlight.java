package GUI;

import app.DbFunctions;
import app.models.Flight;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.text.SimpleDateFormat;

public class FrameAddFlight extends JFrame implements ActionListener {
    JLabel labelDestination,labelFreeTickets, labelDate, labelPriceSeats, labelNumberSeats;
    JPanel panelDestination, panelDate, panelPriceSeats, panelNumberSeats,panelContainer;
    JButton buttonEnter, buttonBack;
    JTextField  textPriceSeats, textNumberSeats;
    JDatePickerImpl pickerDate;
    String formattedDate;
    JComboBox comboBoxDestination;
    JSpinner timeSpinner;
    Flight flightOld;
    DbFunctions db;
    Connection conn;
    Date selectedDate,selectedTime;
    String[] destination;
    boolean isChange;
    public FrameAddFlight(Connection conn, boolean isChange){
        db =new DbFunctions();
        this.conn= conn;
        this.isChange=isChange;
        panelStart();
    }

    public FrameAddFlight(Connection conn, Flight flightOld, boolean isChange){
        db =new DbFunctions();
        this.conn= conn;
        this.isChange=isChange;
        this.flightOld=flightOld;
        panelStart();
        textPriceSeats.setText(String.valueOf(flightOld.getPriceOfSeat()));
        textNumberSeats.setText(String.valueOf(flightOld.getTotalNumberOfSeats()));
        int index=0;
        for(int i=0; i<destination.length;i++){
            if(Objects.equals(flightOld.getOriginCity(), destination[i].substring(11, 14)) && Objects.equals(flightOld.getDestinationCity(), destination[i].substring(33, 36))){
                index=i;
            }
        }
        comboBoxDestination.setSelectedIndex(index);
        //System.out.println("free tickets(idFlight="+flightOld.getIdFlight()+"): "+db.searchFreeTickets(conn, flightOld.getIdFlight()));
        String line="**You can reduce the total number of tickets by "+db.searchFreeTickets(conn, flightOld.getIdFlight());
        labelFreeTickets.setText(line);
        System.out.println("idFlights: "+flightOld.getIdFlight());
        System.out.println("index:"+index);
        System.out.println("OriginCity: --"+destination[0].substring(11, 14)+"--");
        System.out.println("DestinationCity: --"+destination[0].substring(33, 36)+"--");
        System.out.println("OriginCity: "+ flightOld.getOriginCity());
        System.out.println("DestinationCity: "+ flightOld.getDestinationCity());
        System.out.println("Date: "+ flightOld.getFlightDate());
        System.out.println("PriceOfSeat: "+ flightOld.getPriceOfSeat());
        System.out.println("NumberOfSeats "+ flightOld.getTotalNumberOfSeats());
    }
    public void panelStart(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,  400);

        destination=db.readDestinations(conn);
        comboBoxDestination =new JComboBox(destination);
        comboBoxDestination.addActionListener(this);
        comboBoxDestination.setPreferredSize(new Dimension(200,30));

        buttonEnter = new JButton("Add/Change flight");
        buttonEnter.addActionListener(this);

        buttonBack = new JButton("Back");
        buttonBack.addActionListener(this);

        panelDate = new JPanel();
        panelDestination = new JPanel();
        panelPriceSeats = new JPanel();
        panelNumberSeats = new JPanel();
        panelContainer = new JPanel();
        labelFreeTickets=new JLabel();
        labelDestination = new JLabel();
        labelDate = new JLabel();
        labelPriceSeats = new JLabel();
        labelNumberSeats = new JLabel();

        textNumberSeats = new JTextField();
        textPriceSeats = new JTextField();

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        pickerDate = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        pickerDate.setPreferredSize(new Dimension(130,30));
        pickerDate.setBackground(Color.white);

        pickerDate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try{
                    formattedDate = dateFormat.format(pickerDate.getModel().getValue());
                    selectedDate = (Date) pickerDate.getModel().getValue();
                } catch (Exception x){
                    formattedDate=null;
                }
            }
        });

        SpinnerDateModel timeModel = new SpinnerDateModel();
        timeModel.setCalendarField(Calendar.MINUTE);
        JSpinner timeSpinner = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(timeEditor);

        timeSpinner.addChangeListener(e -> {
            selectedTime = (Date) timeSpinner.getValue();
        });

        textPriceSeats.setPreferredSize(new Dimension(200,30));
        textNumberSeats.setPreferredSize(new Dimension(200,30));

        labelDestination.setText("Destination*:        ");
        labelDate.setText("Date and Time*:    ");
        labelPriceSeats.setText("Price seat*:          ");
        labelNumberSeats.setText("Number seats*:    ");

        panelDestination.setLayout(new FlowLayout());
        panelDate.setLayout(new FlowLayout());
        panelPriceSeats.setLayout(new FlowLayout());
        panelNumberSeats.setLayout(new FlowLayout());
        panelDestination.add(labelDestination);
        panelDestination.add(comboBoxDestination);
        panelDate.add(labelDate);
        panelDate.add(pickerDate);
        panelDate.add(timeSpinner);


        panelPriceSeats.add(labelPriceSeats);
        panelPriceSeats.add(textPriceSeats);
        panelNumberSeats.add(labelNumberSeats);
        panelNumberSeats.add(textNumberSeats);

        panelDestination.setBackground(Color.white);
        panelDate.setBackground(Color.white);
        panelPriceSeats.setBackground(Color.white);
        panelNumberSeats.setBackground(Color.white);
        panelContainer.setBackground(Color.white);

        buttonBack.setBounds(300,300,150,30);
        buttonEnter.setBounds(145,300,150,30);

        panelDestination.setBounds(50,20,400,50);
        panelPriceSeats.setBounds(50,120,400,50);
        panelNumberSeats.setBounds(50,170,400,50);
        panelDate.setBounds(50,70,400,50);
        labelFreeTickets.setBounds(50,220,400,30);

        panelContainer.setLayout(null);
        panelContainer.setSize(500,  400);
        panelContainer.add(panelDestination);
        panelContainer.add(panelDate);
        panelContainer.add(panelPriceSeats);
        panelContainer.add(panelNumberSeats);
        panelContainer.add(buttonBack);
        panelContainer.add(buttonEnter);
        panelContainer.add(labelFreeTickets);
        this.add(panelContainer);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonEnter){

            if (selectedTime == null||selectedDate == null) {
                JOptionPane.showMessageDialog(this, "Date and Time must be selected", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Calendar selectedDateTime = Calendar.getInstance();
            if (selectedDate != null) {
                selectedDateTime.setTime(selectedDate);
                Calendar timeCalendar = Calendar.getInstance();
                timeCalendar.setTime(selectedTime);
                selectedDateTime.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
                selectedDateTime.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
                selectedDateTime.set(Calendar.SECOND, timeCalendar.get(Calendar.SECOND));
            }

            Calendar now = Calendar.getInstance();
            String formattedDateTime="";
            if (selectedDateTime.after(now)) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                formattedDateTime = format.format(selectedDateTime.getTime());

            } else {
                JOptionPane.showMessageDialog(this, "The selected date and time must be in the future.", "Invalid Date/Time", JOptionPane.ERROR_MESSAGE);
            }

            if((textPriceSeats.getText().isEmpty()) || (textNumberSeats.getText().isEmpty()) || (formattedDate == null)) {
                JOptionPane.showMessageDialog(null, "Enter all data", "Error", JOptionPane.WARNING_MESSAGE);
            }else{


                String checkPrice = textPriceSeats.getText();
                boolean correctPrice = true;

                try {
                    if ((Double.valueOf(checkPrice) <= 0) || (Double.valueOf(checkPrice) > 999999)) {
                        correctPrice = false;
                    }
                } catch (Exception r) {
                    JOptionPane.showMessageDialog(null, "Invalid price seat", "Error", JOptionPane.WARNING_MESSAGE);
                }
                if (!correctPrice) {
                    JOptionPane.showMessageDialog(null, "Invalid price seat", "Error", JOptionPane.WARNING_MESSAGE);
                }

                String checkNumberSeats = textNumberSeats.getText();
                boolean correctNumberSeats = true;
                int numberSeatsCorrect=0;
                if (isChange) numberSeatsCorrect=flightOld.getTotalNumberOfSeats()-db.searchFreeTickets(conn, flightOld.getIdFlight());

                try {
                    if ((Integer.valueOf(checkNumberSeats) < numberSeatsCorrect) || (Integer.valueOf(checkNumberSeats) > 350)) {
                        correctNumberSeats = false;
                    }
                } catch (Exception r) {
                    JOptionPane.showMessageDialog(null, "Invalid number seats", "Error", JOptionPane.WARNING_MESSAGE);
                }
                if (!correctNumberSeats) {
                    JOptionPane.showMessageDialog(null, "Invalid number seats", "Error", JOptionPane.WARNING_MESSAGE);
                }

                if (correctPrice  && correctNumberSeats && !formattedDateTime.isEmpty()&&!isChange) {

                    try{
                         int idDestination=comboBoxDestination.getSelectedIndex()+1;
                        db.insert_row_flightToDB(conn, new Flight(idDestination,formattedDateTime,Double.valueOf(textPriceSeats.getText()),Integer.valueOf(textNumberSeats.getText())));
                        JOptionPane.showMessageDialog(null, "Flight inserted", "Congratulations", JOptionPane.PLAIN_MESSAGE);
                        this.dispose();
                    }catch (Exception q){
                        JOptionPane.showMessageDialog(null, q, "Error", JOptionPane.WARNING_MESSAGE);
                    }

                }

                if (correctPrice  && correctNumberSeats && !formattedDateTime.isEmpty()&&isChange) {

                    try{
                        int idDestination=comboBoxDestination.getSelectedIndex()+1;
                        System.out.println("free tickets(idFlight="+flightOld.getIdFlight()+"): "+db.searchFreeTickets(conn, flightOld.getIdFlight()));
                        db.update_row_flight(conn, flightOld,new Flight(idDestination,formattedDateTime,Double.valueOf(textPriceSeats.getText()),Integer.valueOf(textNumberSeats.getText())));
                        //JOptionPane.showMessageDialog(null, "Flight chan", "Congratulations", JOptionPane.PLAIN_MESSAGE);
                        this.dispose();
                    }catch (Exception q){
                        JOptionPane.showMessageDialog(null, q, "Error", JOptionPane.WARNING_MESSAGE);
                    }

                }

            }

        }
        if(e.getSource()==buttonBack){
            this.dispose();
        }
    }
}
