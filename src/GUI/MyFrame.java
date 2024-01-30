package GUI;


import app.DbFunctions;
import app.TravelAgency;
import app.models.Flight;
import app.models.User;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class MyFrame extends JFrame implements ActionListener {
    JLabel labelImage,labelPicker,label,labelResult, labelName, labelNamesResult;
    JPanel panelImage,panelDate, panelUser, panelSearch, panelButton, panelResult, panelFilter;
    JScrollPane scroll;
    final String[] flightSelectedToDestination={null};
    final String[] flightSelectedAround={null};
    JComboBox comboBoxOrigen, comboBoxDestination, comboBoxFilter;
    JSpinner spinerSeats;
    //JDatePicker pickerThere, pickerBack;
    JButton buttonChangeFlight,buttonAddDestination,buttonAddFlight,buttonSearch, buttonLogIn, buttonDate,buttonProfile, buttonExit, buttonFilter,buttonRegistration,buttonBuy,buttonSelectedToDestination, buttonSelectedAround;
    String formattedDate,formattedDateRound;
    JDatePickerImpl pickerRound,pickerThere;
    JTextArea console;
    ArrayList<JButton> buttons;
    ArrayList<Flight> result, resultRound;
    DbFunctions db;
    Connection conn;
     public MyFrame(Connection conn){
         db = new DbFunctions();
         this.conn = conn;
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setSize(920, 800);

         this.setLayout(null);
         String[] origenCity=db.readOriginCity(conn);
         comboBoxOrigen =new JComboBox(origenCity);
         comboBoxOrigen.addActionListener(this);
         comboBoxOrigen.setPreferredSize(new Dimension(180,40));
         comboBoxOrigen.insertItemAt("Original airport",0);
         comboBoxOrigen.setSelectedIndex(0);

         String[] destinationCity=db.readDestinationCity(conn);
         comboBoxDestination =new JComboBox(destinationCity);
         comboBoxDestination.addActionListener(this);
         comboBoxDestination.setPreferredSize(new Dimension(180,40));
         comboBoxDestination.insertItemAt("Airport of destination",0);
         comboBoxDestination.setSelectedIndex(0);

         String[] filter= {"Filter by price - from lowest to highest","Filter by price - from highest to lowest"};
         comboBoxFilter =new JComboBox(filter);
         comboBoxFilter.addActionListener(this);
         comboBoxFilter.setBounds(20,5,300,30);

         buttonFilter= new JButton("Filter");
         buttonFilter.setBounds(320,5,100,30);
         buttonFilter.addActionListener(this);

         buttonBuy= new JButton("Buy");
         buttonBuy.setBounds(750,5,100,30);
         buttonBuy.addActionListener(this);

         buttonSearch = new JButton("Search");
         buttonSearch.setPreferredSize(new Dimension(150,40));
         buttonSearch.addActionListener(this);

         buttonDate = new JButton("Roundtrip");
         buttonDate.setPreferredSize(new Dimension(100,40));
         buttonDate.addActionListener(this);

         buttonLogIn = new JButton("LogIn");
         buttonLogIn.setBackground(Color.white);
         buttonLogIn.setFocusable(false);
         buttonLogIn.setBounds(600,10,120,30);
         buttonLogIn.addActionListener(this);

         buttonRegistration = new JButton("Registration");
         buttonRegistration.setBackground(Color.white);
         buttonRegistration.setFocusable(false);
         buttonRegistration.setBounds(730,10,120,30);
         buttonRegistration.addActionListener(this);

         UtilDateModel model = new UtilDateModel();
         Properties p = new Properties();
         p.put("text.today", "Today");
         p.put("text.month", "Month");
         p.put("text.year", "Year");
         JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
         JDatePickerImpl pickerThere = new JDatePickerImpl(datePanel, new DateLabelFormatter());

         pickerThere.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                 try{
                     formattedDate = dateFormat.format(pickerThere.getModel().getValue());
                 } catch (Exception x){
                     formattedDate=null;
                 }
             }
         });

         UtilDateModel model2 = new UtilDateModel();
         Properties p2 = new Properties();
         p2.put("text.today", "Today");
         p2.put("text.month", "Month");
         p2.put("text.year", "Year");
         JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
         JDatePickerImpl pickerRound = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
        
         pickerRound.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                 try{
                     formattedDateRound = dateFormat2.format(pickerRound.getModel().getValue());
                 } catch (Exception x){
                     formattedDateRound=null;
                 }
             }
         });

         spinerSeats= new JSpinner(new SpinnerNumberModel());
         spinerSeats.setPreferredSize(new Dimension(100,40));

         panelFilter=new JPanel();
         panelImage=new JPanel();
         labelResult = new JLabel();
         panelDate = new JPanel();
         panelButton=new JPanel();
         panelSearch = new JPanel();
         panelUser = new JPanel();
         labelImage = new JLabel();
         labelPicker = new JLabel();
         label = new JLabel();
         labelResult=new JLabel();
         labelNamesResult=new JLabel("                 Original airport                Airport of destination                            Date          Time                                       Time of flight                              Price");
         panelResult=new JPanel();

         JScrollPane scroll = new JScrollPane(panelResult);
         scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

         panelImage.setBounds(0,0, 900,300);
         panelUser.setBounds(0,300, 900,80);
         panelSearch.setBounds(0,380, 900,100);
         panelFilter.setBounds(0,480, 900,70);
         labelNamesResult.setBounds(0,560, 900,20);
         scroll.setBounds(0,580, 900,180);

        labelNamesResult.setBackground(Color.white);
        panelFilter.setBackground(Color.white);
         panelImage.setBackground(Color.white);
         panelUser.setBackground(Color.white);
         panelSearch.setBackground(Color.white);
         labelImage.setIcon(new ImageIcon("src/GUI/banner.jpg"));
         panelImage.add(labelImage);
         panelUser.setLayout(null);

         if (User.isStatusUser()) {
             panelUser.removeAll();
             buttonProfile = new JButton("Profile");
             buttonProfile.setPreferredSize(new Dimension(100, 40));
             buttonProfile.setBackground(Color.white);
             buttonProfile.setFocusable(false);
             buttonProfile.setBounds(650, 10, 100, 30);
             buttonProfile.addActionListener(this);

             buttonExit = new JButton("Exit");
             buttonExit.setPreferredSize(new Dimension(100, 40));
             buttonExit.setBackground(Color.white);
             buttonExit.setFocusable(false);
             buttonExit.setBounds(750, 10, 100, 30);
             buttonExit.addActionListener(this);

             if (User.isIsManager()) labelName = new JLabel("Hi manager " + User.getNameStaticUser());
             else labelName = new JLabel("Hi user " + User.getNameStaticUser());
             labelName.setBounds(500, 10, 150, 30);
             panelUser.add(labelName);
             panelUser.add(buttonProfile);
             panelUser.add(buttonExit);

             if (User.isIsManager()){
                 buttonChangeFlight = new JButton("Change flight");
                 buttonChangeFlight.setBackground(new Color(217, 214, 145));
                 buttonChangeFlight.setFocusable(false);
                 buttonChangeFlight.setBounds(30, 10, 150, 30);
                 buttonChangeFlight.addActionListener(this);
                 buttonChangeFlight.setEnabled(false);

                 buttonAddFlight = new JButton("Add flight");
                 buttonAddFlight.setBackground(new Color(217, 214, 145));
                 buttonAddFlight.setFocusable(false);
                 buttonAddFlight.setBounds(185, 10, 150, 30);
                 buttonAddFlight.addActionListener(this);

                 buttonAddDestination = new JButton("Add destination");
                 buttonAddDestination.setBackground(new Color(217, 214, 145));
                 buttonAddDestination.setFocusable(false);
                 buttonAddDestination.setBounds(340, 10, 150, 30);
                 buttonAddDestination.addActionListener(this);

                 panelUser.add(buttonChangeFlight);
                 panelUser.add(buttonAddDestination);
                 panelUser.add(buttonAddFlight);
             }
         }else {
             panelUser.add(buttonLogIn);
             panelUser.add(buttonRegistration);
         }

         panelFilter.setLayout(null);

         panelFilter.add(comboBoxFilter);
         panelFilter.add(buttonFilter);
         panelFilter.add(buttonBuy);
         comboBoxFilter.setVisible(false);
         buttonFilter.setVisible(false);
         buttonBuy.setVisible(false);

         panelDate.setLayout(new BorderLayout());
         panelDate.add(pickerThere,BorderLayout.NORTH);
         panelDate.add(pickerRound,BorderLayout.SOUTH);
         panelSearch.setLayout(new FlowLayout());
         panelSearch.add(comboBoxOrigen);
         panelSearch.add(comboBoxDestination);
         panelSearch.add(spinerSeats);
         panelSearch.add(panelDate);
         panelSearch.add(buttonSearch);

         this.add(panelImage);
         this.add(panelUser);
         this.add(panelSearch);
         this.add(panelFilter);
         this.add(labelNamesResult);
         this.add(scroll);
         this.setVisible(true);
     }

    @Override

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonLogIn){
            new FrameLogIn(conn);
            this.dispose();
        }


        if(e.getSource()==buttonChangeFlight){
            new FrameAddFlight(conn, db.search_flight(conn,flightSelectedToDestination[0].substring(0, 3),flightSelectedToDestination[0].substring(43, 46),flightSelectedToDestination[0].substring(86, 108)),true);

        }


        if(e.getSource()==buttonAddFlight){
            new FrameAddFlight(conn,false);
        }

        if(e.getSource()==buttonAddDestination){
            new FrameAddDestination(conn);
            //this.dispose();
        }

        if(e.getSource()==buttonSearch){
            boolean correctDateOrigen = true;
            boolean correctDateAround = true;
            boolean correctNumberTickets=true;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            if(formattedDate!=null) {

                LocalDate parsedDate = LocalDate.parse(formattedDate, formatter);
                LocalDate today = LocalDate.now();

                if (parsedDate.isBefore(today)) {
                    correctDateOrigen = false;
                    JOptionPane.showMessageDialog(null, "Date is not correct", "Error", JOptionPane.WARNING_MESSAGE);
                }

                if (formattedDateRound != null) {
                    LocalDate departureDate = LocalDate.parse(formattedDate, formatter);
                    LocalDate returnDate = LocalDate.parse(formattedDateRound, formatter);
                    if (returnDate.isBefore(departureDate)) {
                        correctDateAround = false;
                        JOptionPane.showMessageDialog(null, "Date around is not correct", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            if ((Integer)spinerSeats.getValue()<1){
                correctNumberTickets=false;
                JOptionPane.showMessageDialog(null, "Number of tickets is not correct", "Error", JOptionPane.WARNING_MESSAGE);
            }
            if(correctDateOrigen&&correctDateAround&&correctNumberTickets){
                comboBoxFilter.setVisible(true);
                buttonFilter.setVisible(true);
                buttonBuy.setVisible(true);
                TravelAgency travelAgency = new TravelAgency(conn,(String) comboBoxOrigen.getSelectedItem(),
                        (String) comboBoxDestination.getSelectedItem(),
                        (Integer) spinerSeats.getValue(),
                        formattedDate,
                        formattedDateRound);

                result=travelAgency.getResult();
                resultRound=travelAgency.getResultRound();
                printResult();
            }
        }

        if(e.getSource()==buttonRegistration){
            new FrameRegistration(conn);
            this.dispose();
        }
        if(e.getSource()==buttonProfile){
            new FrameProfile(conn);
        }

        if(e.getSource()==buttonExit){
            User.setStatusUser(false);
            this.dispose();
            new MyFrame(conn);
        }

        if(e.getSource()==buttonBuy){
            if (User.isStatusUser()) {
                if(flightSelectedAround[0]==null) new FrameBookingAndPayment(conn, db.search_flight(conn,flightSelectedToDestination[0].substring(0, 3),flightSelectedToDestination[0].substring(43, 46),flightSelectedToDestination[0].substring(86, 108)),null, (Integer) spinerSeats.getValue());
               else new FrameBookingAndPayment(conn, db.search_flight(conn,flightSelectedToDestination[0].substring(0, 3),flightSelectedToDestination[0].substring(43, 46),flightSelectedToDestination[0].substring(86, 108)),db.search_flight(conn,flightSelectedAround[0].substring(0, 3),flightSelectedAround[0].substring(43, 46),flightSelectedAround[0].substring(86, 108)), (Integer) spinerSeats.getValue());
                flightSelectedAround[0]=null;
            }else {
                JOptionPane.showMessageDialog(null,"Please, register or login","no identification",JOptionPane.WARNING_MESSAGE);
            }
             }

        if(e.getSource()==buttonFilter) {
            if (comboBoxFilter.getSelectedItem()=="Filter by price - from lowest to highest"){
                 result=filterByPriceLtoH(result);
                resultRound=filterByPriceLtoH(resultRound);
                printResult();
            }
            if (comboBoxFilter.getSelectedItem()=="Filter by price - from highest to lowest"){
                result=filterByPriceHtoL(result);
                resultRound=filterByPriceHtoL(resultRound);
                printResult();
            }

        }
    }
        public ArrayList<Flight> filterByPriceLtoH(ArrayList<Flight> flights){
         if (flights==null || flights.isEmpty()){
             return new ArrayList<>();
         } else {
             ArrayList<Flight> sortedFlights = new ArrayList<>(flights);
             sortedFlights.sort(Comparator.comparingDouble(Flight::getPriceOfSeat).reversed());
             return sortedFlights;
         }
        }

    public ArrayList<Flight> filterByPriceHtoL(ArrayList<Flight> flights){
        if (flights==null || flights.isEmpty()){
            return new ArrayList<>();
        } else {
            ArrayList<Flight> sortedFlights = new ArrayList<>(flights);
            sortedFlights.sort(Comparator.comparingDouble(Flight::getPriceOfSeat));
            return sortedFlights;
        }
    }
        public void printResult(){
            panelButton.removeAll();
            ArrayList<JButton> buttonsToDestination = new ArrayList<>();
            ArrayList<JButton> buttonsAround = new ArrayList<>();
            if(formattedDateRound!=null&&formattedDate!=null) panelButton.setLayout(new GridLayout((result.size()+resultRound.size()+2),1));
            else panelButton.setLayout(new GridLayout((result.size()+1),1));
            JLabel label1=new JLabel("Tickets to destination:");
            label1.setPreferredSize(new Dimension(870,30));

            panelButton.add(label1);
            for(int i=0;i<result.size();i++){
                buttonsToDestination.add(new JButton(result.get(i).toString()));
                buttonsToDestination.get(i).setPreferredSize(new Dimension(870,20));
                buttonsToDestination.get(i).addActionListener(this);
                panelButton.add(buttonsToDestination.get(i));
            }
            if(formattedDateRound!=null&&formattedDate!=null){
                JLabel labelRound=new JLabel("Tickets back:");
                labelRound.setPreferredSize(new Dimension(870,30));
                panelButton.add(labelRound);
                for(int i=0;i<resultRound.size();i++){
                    buttonsAround.add(new JButton(resultRound.get(i).toString()));
                    buttonsAround.get(i).setPreferredSize(new Dimension(870,20));
                    panelButton.add(buttonsAround.get(i));
                }
            }
            panelFilter.setVisible(true);
            panelResult.add(panelButton);

            this.setVisible(true);

            final JButton[] lastPressedButton = {null};
            for (int i = 0; i < buttonsToDestination.size(); i++) {
                JButton button = buttonsToDestination.get(i);
                button.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (lastPressedButton[0] != null) {
                                    lastPressedButton[0].setBackground(null);
                                }
                                button.setBackground(new Color(217, 214, 145));
                                lastPressedButton[0] = button;
                                flightSelectedToDestination[0]= button.getText();
                                if (User.isIsManager()) buttonChangeFlight.setEnabled(true);
                                //System.out.println(User.isIsManager());
                            }
                        });
            };

            final JButton[] lastPressedButtonRound = {null};
            for (int i = 0; i < buttonsAround.size(); i++) {
                JButton button = buttonsAround.get(i);
                button.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (lastPressedButtonRound[0] != null) {
                                    lastPressedButtonRound[0].setBackground(null);
                                }
                                button.setBackground(new Color(217, 214, 145));
                                lastPressedButtonRound[0] = button;
                                flightSelectedAround[0]= button.getText();
                            }
                        });
            };

        }
}
