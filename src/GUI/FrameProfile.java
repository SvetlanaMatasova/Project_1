package GUI;


import app.DbFunctions;
import app.models.Reservation;
import app.models.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.*;


public class FrameProfile extends JFrame implements ActionListener {
    JLabel labelImage,label,labelResult, labelName, labelNamesResult;
    JPanel panelImage, panelUser,  panelButton, panelResult, panelFilter;
    JScrollPane scroll;
    final String[] reservationSelected ={null};

    JButton  buttonExit, buttonDelete,buttonRefresh,buttonPay;
    Reservation reservation;
    JTextArea console;
    ArrayList<JButton> buttons;
    ArrayList<Reservation> resultBooking=null, resultPayment=null;
    DbFunctions db;
    Connection conn;
    public FrameProfile(Connection conn){
        db = new DbFunctions();
        this.conn = conn;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(920, 800);
        this.setLayout(null);

        buttonDelete= new JButton("Delete");
        buttonDelete.setBounds(645,5,100,30);
        buttonDelete.setFocusable(false);
        buttonDelete.addActionListener(this);

        buttonPay= new JButton("Pay");
        buttonPay.setBounds(750,5,100,30);
        buttonPay.setFocusable(false);
        buttonPay.addActionListener(this);

        panelFilter=new JPanel();
        panelImage=new JPanel();
        labelResult = new JLabel();
        panelButton=new JPanel();
        panelUser = new JPanel();
        labelImage = new JLabel();

        label = new JLabel();
        labelResult=new JLabel();
        labelNamesResult=new JLabel("                Booking/Payment time                   Original airport     Airport of destination                 Date          Time                      Number of tickets             Price");
        panelResult=new JPanel();

        JScrollPane scroll = new JScrollPane(panelResult);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panelImage.setBounds(0,0, 900,300);
        panelUser.setBounds(0,300, 900,40);
        panelFilter.setBounds(0,340, 900,40);
        labelNamesResult.setBounds(0,380, 900,20);
        scroll.setBounds(0,400, 900,350);

        labelNamesResult.setBackground(Color.white);
        panelFilter.setBackground(Color.white);
        panelImage.setBackground(Color.white);
        panelUser.setBackground(Color.white);
        labelImage.setIcon(new ImageIcon("src/GUI/banner.jpg"));
        panelImage.add(labelImage);
        panelUser.setLayout(null);


            buttonExit = new JButton("Back");
            buttonExit.setBackground(Color.white);
            buttonExit.setBounds(50, 5, 100, 30);
            buttonExit.addActionListener(this);

        buttonRefresh= new JButton("Refresh");
        buttonRefresh.setBounds(155,5,100,30);
        buttonRefresh.setBackground(Color.white);
        buttonRefresh.setFocusable(false);
        buttonRefresh.addActionListener(this);

            if (User.isIsManager()) labelName = new JLabel("Hi manager " + User.getNameStaticUser());
            else labelName = new JLabel("Hi user " + User.getNameStaticUser());
            labelName.setBounds(700, 5, 150, 30);
            panelUser.add(labelName);
            panelUser.add(buttonExit);
        panelUser.add(buttonRefresh);


        panelFilter.setLayout(null);
        buttonDelete.setEnabled(false);
        buttonPay.setEnabled(false);

        panelFilter.add(buttonDelete);
        panelFilter.add(buttonPay);


        this.add(panelImage);
        this.add(panelUser);
        this.add(panelFilter);
        this.add(labelNamesResult);
        this.add(scroll);
        this.setVisible(true);

        resultBooking=db.searchBooking(conn);
        resultPayment=db.searchPayment(conn);

        printResult();

    }

    @Override

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonExit){
            this.dispose();
        }
        if(e.getSource()==buttonPay) {
            for(int k=0; k<resultBooking.size();k++){
                if (Objects.equals(resultBooking.get(k).getDateReservation(), reservationSelected[0].substring(3, 25)) && Objects.equals(resultBooking.get(k).getOriginCity(), reservationSelected[0].substring(53, 56)) && Objects.equals(resultBooking.get(k).getDestinationCity(), reservationSelected[0].substring(84, 87))){
                    new FramePayment(conn, resultBooking.get(k), null,true);
                    break;
                }
            }
        }

            if(e.getSource()==buttonDelete){
                for(int k=0; k<resultBooking.size();k++){
                    if (Objects.equals(resultBooking.get(k).getDateReservation(), reservationSelected[0].substring(3, 25)) && Objects.equals(resultBooking.get(k).getOriginCity(), reservationSelected[0].substring(53, 56)) && Objects.equals(resultBooking.get(k).getDestinationCity(), reservationSelected[0].substring(84, 87))){
                        reservation=resultBooking.get(k);
                        db.deleteReservation(conn,reservation);
                        break;
                    }
                }
                new FrameProfile(conn);
                this.dispose();
        }
        if(e.getSource()==buttonRefresh){
            this.dispose();
            new FrameProfile(conn);
        }
    }

    public void printResult(){
        if(panelButton!=null)panelButton.removeAll();
        ArrayList<JButton> buttonsBooking = new ArrayList<>();
        ArrayList<JButton> buttonsPayment = new ArrayList<>();

        if(resultBooking!=null&&resultPayment!=null) panelButton.setLayout(new GridLayout((resultBooking.size()+resultPayment.size()+2),1));
        if(resultBooking==null&&resultPayment==null) panelButton.setLayout(new GridLayout(2,1));
        if(resultBooking!=null&&resultPayment==null) panelButton.setLayout(new GridLayout((resultBooking.size()+2),1));
        if(resultBooking==null&&resultPayment!=null) panelButton.setLayout(new GridLayout((resultPayment.size()+2),1));

        JLabel label1=new JLabel("Booking:");
        label1.setPreferredSize(new Dimension(870,30));


        panelButton.add(label1);

        for(int i=0;i<resultBooking.size();i++){
            buttonsBooking.add(new JButton(resultBooking.get(i).toString()));
            buttonsBooking.get(i).setPreferredSize(new Dimension(870,20));
            buttonsBooking.get(i).addActionListener(this);
            panelButton.add(buttonsBooking.get(i));
        }

        if(resultPayment!=null){
            JLabel labelRound=new JLabel("Payment:");
            labelRound.setPreferredSize(new Dimension(870,30));
            panelButton.add(labelRound);
            for(int i=0;i<resultPayment.size();i++){
                buttonsPayment.add(new JButton(resultPayment.get(i).toString()));
                buttonsPayment.get(i).setPreferredSize(new Dimension(870,20));
                panelButton.add(buttonsPayment.get(i));
            }
        }
        if(resultBooking==null&&resultPayment!=null){
            JLabel labelRound=new JLabel("Payment:");
            labelRound.setPreferredSize(new Dimension(870,30));
            panelButton.add(labelRound);
            for(int i=0;i<resultPayment.size();i++){
                buttonsPayment.add(new JButton(resultPayment.get(i).toString()));
                buttonsPayment.get(i).setPreferredSize(new Dimension(870,20));
                panelButton.add(buttonsPayment.get(i));
            }
        }
        panelResult.add(panelButton);

        this.setVisible(true);

        final JButton[] lastPressedButton = {null};
        for (int i = 0; i < buttonsBooking.size(); i++) {
            JButton button = buttonsBooking.get(i);
            button.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (lastPressedButton[0] != null) {
                                lastPressedButton[0].setBackground(null);
                            }
                            button.setBackground(new Color(217, 214, 145));
                            lastPressedButton[0] = button;
                            reservationSelected[0]= button.getText();
                            buttonDelete.setEnabled(true);
                            buttonPay.setEnabled(true);
                        }
                    });
        };



    }
}
