package GUI;

import app.DbFunctions;
import app.models.FlightDestination;
import app.models.Reservation;
import app.models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Objects;

public class FrameAddDestination extends JFrame implements ActionListener {
    JLabel labelImage, labelOrigenCity, labelDestinationCity, labelTimeFlights, labelHeader,labelHeaderOutput;
    JPanel panelImage,panelButton, panelUser, panelEnter, panelOutput, panelCenter, panelOrigenCity, panelDestinationCity, panelTimeFlights;
    JButton buttonEnter, buttonBack, buttonDelete, buttonChange;
    JTextField textTimeFlights, textOrigenCity,textDestinationCity;
    DbFunctions db;
    JScrollPane scroll;
    Connection conn;
    final String[] destinationSelected={null};
    ArrayList<JButton> buttons;
    ArrayList<FlightDestination> result=null;
    public FrameAddDestination(Connection conn){
        db =new DbFunctions();
        this.conn= conn;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900,  700);
        this.setLayout(new BorderLayout());

        buttonEnter = new JButton("Add destination");
        buttonEnter.setPreferredSize(new Dimension(120,40));
        buttonEnter.addActionListener(this);

        buttonBack = new JButton("Back");
        buttonBack.setPreferredSize(new Dimension(120,40));
        buttonBack.addActionListener(this);

        buttonDelete = new JButton("Delete");
        buttonDelete.setPreferredSize(new Dimension(120,40));
        buttonDelete.addActionListener(this);
        buttonDelete.setEnabled(false);

        buttonChange = new JButton("Change destination");
        buttonChange.setPreferredSize(new Dimension(120,40));
        buttonChange.addActionListener(this);
        buttonChange.setEnabled(false);

        panelImage=new JPanel();
        panelUser = new JPanel();
        panelEnter = new JPanel();
        panelCenter= new JPanel();
        panelOutput= new JPanel();
        panelDestinationCity = new JPanel();
        panelOrigenCity = new JPanel();
        panelTimeFlights = new JPanel();
        panelButton=new JPanel();

        JScrollPane scroll = new JScrollPane(panelOutput);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(20,350, 440,200);

        labelImage = new JLabel();
        labelOrigenCity = new JLabel();
        labelDestinationCity = new JLabel();
        labelTimeFlights = new JLabel();
        labelHeader= new JLabel("Add a new direction / Change the selected direction:");
        labelHeaderOutput= new JLabel("                 Original airport        Airport of destination         Time of flight ");
        textTimeFlights = new JTextField();
        textTimeFlights.setPreferredSize(new Dimension(180,30));

        textOrigenCity = new JTextField();
        textOrigenCity.setPreferredSize(new Dimension(180,30));

        textDestinationCity = new JTextField();
        textDestinationCity.setPreferredSize(new Dimension(180,30));

             labelOrigenCity.setText("Original airport:              ");
        labelDestinationCity.setText("Airport of destination:   ");
            labelTimeFlights.setText("Time of flight:                 ");

        panelOrigenCity.setLayout(new FlowLayout());

        panelDestinationCity.setLayout(new FlowLayout());
        panelTimeFlights.setLayout(new FlowLayout());

        panelOrigenCity.add(labelOrigenCity);
        panelOrigenCity.add(textOrigenCity);
        panelDestinationCity.add(labelDestinationCity);
        panelDestinationCity.add(textDestinationCity);
        panelTimeFlights.add(labelTimeFlights);
        panelTimeFlights.add(textTimeFlights);


        panelImage.setPreferredSize(new Dimension(100,300));
        panelUser.setPreferredSize(new Dimension(500,100));


        panelImage.setBackground(Color.white);
        panelUser.setBackground(Color.white);
        panelEnter.setBackground(Color.white);
        panelCenter.setBackground(Color.white);

        labelImage.setIcon(new ImageIcon("src/GUI/banner.jpg"));
        panelImage.add(labelImage);
        panelOrigenCity.setBackground(Color.white);
        panelDestinationCity.setBackground(Color.white);
        panelTimeFlights.setBackground(Color.white);

        panelUser.add(buttonBack);
        panelUser.add(buttonEnter);
        panelUser.add(buttonDelete);
        panelUser.add(buttonChange);
        panelUser.setLayout(null);
        buttonBack.setBounds(705,0,160,30);
        buttonEnter.setBounds(540,0,160,30);
        buttonDelete.setBounds(375,0,160,30);
        buttonChange.setBounds(210,0,160,30);

        panelCenter.setLayout(null);
        labelHeader.setBounds(500,10,400,50);


        labelHeaderOutput.setBounds(20,20,440,20);
        panelOrigenCity.setBounds(450,70,400,50);
        panelDestinationCity.setBounds(450,120,400,50);
        panelTimeFlights.setBounds(450,170,400,50);

        panelCenter.add(labelHeader);
        panelCenter.add(panelOrigenCity);
        panelCenter.add(panelDestinationCity);
        panelCenter.add(labelHeaderOutput);
        panelCenter.add(panelTimeFlights);
        this.add(scroll);
        panelCenter.add(panelEnter);
        this.add(panelUser,BorderLayout.SOUTH);
        this.add(panelCenter,BorderLayout.CENTER);
        this.add(panelImage,BorderLayout.NORTH);
        this.setVisible(true);

        result=db.searchDestinations(conn);
        printResult();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonEnter){

            if (checkCorrectData()){
                if(!db.searchDestination(conn, textOrigenCity.getText(), textDestinationCity.getText())){
                    db.insert_row_destinationToDB(conn, new FlightDestination(textOrigenCity.getText(), textDestinationCity.getText(),textTimeFlights.getText()));

                    this.dispose();
                    new FrameAddDestination(conn);
                } else JOptionPane.showMessageDialog(null,"This direction already exists","Error",JOptionPane.WARNING_MESSAGE);
            }
        }
        if(e.getSource()==buttonChange){

            if (checkCorrectData()){
                //if(!db.searchDestination(conn, textOrigenCity.getText(), textDestinationCity.getText())){
                    db.update_row_destination(conn, new FlightDestination(destinationSelected[0].substring(4, 7), destinationSelected[0].substring(39, 42),destinationSelected[0].substring(74, 79).trim()), new FlightDestination(textOrigenCity.getText(), textDestinationCity.getText(),textTimeFlights.getText()));
                    this.dispose();
                    new FrameAddDestination(conn);
                }
        }
        if(e.getSource()==buttonBack){
            this.dispose();
            new MyFrame(conn);
        }

        if(e.getSource()==buttonDelete){
            for(int k=0; k<result.size();k++){
                if (Objects.equals(result.get(k).getOriginCity(), destinationSelected[0].substring(4, 7)) && Objects.equals(result.get(k).getDestinationCity(), destinationSelected[0].substring(39, 42))){
                    db.deleteDestination(conn, result.get(k));

                    break;
                }
            }
            new FrameAddDestination(conn);
            this.dispose();
        }
    }
public boolean checkCorrectData(){
    String checkTimeFlight=textTimeFlights.getText();
    boolean correctTimeFlight=true;
    for(int i=0;i<checkTimeFlight.length();i++){
        if (!Character.isDigit(checkTimeFlight.charAt(i))) {
            correctTimeFlight=false;
        }
    }
    if(checkTimeFlight.length()>4|| checkTimeFlight.isEmpty()) correctTimeFlight=false;
    if (!correctTimeFlight)JOptionPane.showMessageDialog(null,"Invalid time of flight","Error",JOptionPane.WARNING_MESSAGE);


    String checkOrigenCity=textOrigenCity.getText();
    boolean correctOrigenCity=true;
    for(int i=0;i<checkOrigenCity.length();i++){
        if (!Character.isLetter(checkOrigenCity.charAt(i))||!Character.isUpperCase(checkOrigenCity.charAt(i))) {
            correctOrigenCity=false;
        }
    }
    if(checkOrigenCity.length()!=3) correctOrigenCity=false;
    if (!correctOrigenCity)JOptionPane.showMessageDialog(null,"Invalid original airport","Error",JOptionPane.WARNING_MESSAGE);


    String checkDestinationCity=textDestinationCity.getText();
    boolean correctDestinationCity=true;
    for(int i=0;i<checkDestinationCity.length();i++){
        if (!Character.isLetter(checkDestinationCity.charAt(i))||!Character.isUpperCase(checkDestinationCity.charAt(i))) {
            correctDestinationCity=false;
        }
    }
    if(checkDestinationCity.length()!=3) correctDestinationCity=false;
    if (!correctDestinationCity)JOptionPane.showMessageDialog(null,"Invalid airport of destination","Error",JOptionPane.WARNING_MESSAGE);
    if(checkDestinationCity.equals(checkOrigenCity)) JOptionPane.showMessageDialog(null,"Original airport equals airport of destination","Error",JOptionPane.WARNING_MESSAGE);
    if(correctTimeFlight&&correctDestinationCity&&correctOrigenCity&&!checkDestinationCity.equals(checkOrigenCity)) return true;
    else return false;
}
    public void printResult(){
        if(panelButton!=null)panelButton.removeAll();
        ArrayList<JButton> buttons = new ArrayList<>();

        if(result!=null) panelButton.setLayout(new GridLayout((result.size()),1));
        if(result==null) panelButton.setLayout(new GridLayout(1,1));

        for(int i=0;i<result.size();i++){
            buttons.add(new JButton(result.get(i).toString()));
            buttons.get(i).setPreferredSize(new Dimension(420,20));
            buttons.get(i).addActionListener(this);
            panelButton.add(buttons.get(i));
        }

        panelOutput.add(panelButton);

        this.setVisible(true);

        final JButton[] lastPressedButton = {null};
        for (int i = 0; i < buttons.size(); i++) {
            JButton button = buttons.get(i);
            button.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (lastPressedButton[0] != null) {
                                lastPressedButton[0].setBackground(null);
                            }
                            button.setBackground(new Color(217, 214, 145));
                            lastPressedButton[0] = button;
                            destinationSelected[0]= button.getText();
                            buttonDelete.setEnabled(true);
                            buttonChange.setEnabled(true);
                        }
                    });
        };



    }
}
