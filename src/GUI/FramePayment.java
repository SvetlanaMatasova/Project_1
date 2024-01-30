package GUI;

import GUI.MyFrame;
import app.models.*;
import app.DbFunctions;
import app.models.Payment;
import app.models.Reservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class FramePayment extends JFrame implements ActionListener {
    JLabel labelImage,labelNumberCard,labelDateCard, labelAmount, labelAmountWithNumber, labelCvc;
    JPanel panelImage, panelUser, panelEnter,panelNumberCard, panelDateCard, panelAmount, panelCvc;
    JButton buttonEnter, buttonBack;
    JTextField textNumberCard,textDateCard, textCvc;
    JCheckBox checkBoxSave;
    DbFunctions db;
    Connection conn;
    Reservation reservationOrigen,reservationAround;
    boolean statusReservation;
    public FramePayment(Connection conn, Reservation reservationOrigen, Reservation reservationAround, boolean statusReservation){
        db = new DbFunctions();
        this.conn = conn;
        this.statusReservation=statusReservation;
        this.reservationOrigen=reservationOrigen;
        this.reservationAround=reservationAround;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900,  700);
        this.setLayout(new BorderLayout());

        buttonEnter = new JButton("Payment");
        buttonEnter.setPreferredSize(new Dimension(120,40));
        buttonEnter.addActionListener(this);

        buttonBack = new JButton("Back");
        buttonBack.setPreferredSize(new Dimension(120,40));
        buttonBack.addActionListener(this);

        checkBoxSave=new JCheckBox("You want to save payment details?");

        panelImage=new JPanel();
        panelUser = new JPanel();
        panelEnter = new JPanel();
        panelNumberCard= new JPanel();
        panelDateCard= new JPanel();
        panelAmount= new JPanel();
        panelCvc = new JPanel();


        labelImage = new JLabel();
        labelNumberCard= new JLabel();
        labelDateCard= new JLabel();
        labelAmount= new JLabel();
        labelCvc= new JLabel();

        textNumberCard = new JTextField();
        textDateCard = new JTextField();
        if(reservationAround!=null) labelAmountWithNumber= new JLabel(String.valueOf(Double.valueOf(reservationOrigen.getTotalPrice())+Double.valueOf(reservationAround.getTotalPrice())));
        else labelAmountWithNumber= new JLabel(reservationOrigen.getTotalPrice());
        textCvc= new JTextField();

        textNumberCard.setPreferredSize(new Dimension(250,30));
        labelAmountWithNumber.setPreferredSize(new Dimension(250,30));
        textDateCard.setPreferredSize(new Dimension(250,30));
        textCvc.setPreferredSize(new Dimension(250,30));

        labelNumberCard.setText("Card number:    ");
        labelDateCard.setText("Card date:         ");
        labelCvc.setText("Cvc:                   ");
        labelAmount.setText("Amount:             ");

        panelNumberCard.setLayout(new FlowLayout());
        panelDateCard.setLayout(new FlowLayout());
        panelAmount.setLayout(new FlowLayout());
        panelCvc.setLayout(new FlowLayout());
        panelNumberCard.add(labelNumberCard);
        panelNumberCard.add(textNumberCard);
        panelDateCard.add(labelDateCard);
        panelDateCard.add(textDateCard);
        panelAmount.add(labelAmount);
        panelAmount.add(labelAmountWithNumber);
        panelCvc.add(labelCvc);
        panelCvc.add(textCvc);

        panelImage.setPreferredSize(new Dimension(100,300));
        panelUser.setPreferredSize(new Dimension(500,100));
        panelEnter.setPreferredSize(new Dimension(100,400));

        panelImage.setBackground(Color.white);
        panelUser.setBackground(Color.white);
        panelEnter.setBackground(Color.white);
        labelImage.setIcon(new ImageIcon("src/GUI/banner.jpg"));
        panelImage.add(labelImage);
        panelNumberCard.setBackground(Color.white);
        panelAmount.setBackground(Color.white);
        panelDateCard.setBackground(Color.white);
        panelCvc.setBackground(Color.white);
        checkBoxSave.setBackground(Color.white);

        panelUser.add(buttonBack);
        panelUser.add(buttonEnter);
        panelUser.setLayout(null);
        buttonBack.setBounds(730,0,120,30);
        buttonEnter.setBounds(600,0,120,30);

        panelEnter.setLayout(null);
        panelAmount.setBounds(50,20,400,50);
        panelNumberCard.setBounds(50,70,400,50);
        panelDateCard.setBounds(50,120,400,50);
        panelCvc.setBounds(50,170,400,50);
        checkBoxSave.setBounds(70, 210,400,50);

        panelEnter.add(panelAmount);
        panelEnter.add(panelNumberCard);
        panelEnter.add(panelDateCard);
        panelEnter.add(panelCvc);
        panelEnter.add(checkBoxSave);

        this.add(panelUser,BorderLayout.SOUTH);
        this.add(panelEnter,BorderLayout.CENTER);
        this.add(panelImage,BorderLayout.NORTH);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
//
        if(e.getSource()==buttonEnter) {

            String checkNumberCard = textNumberCard.getText();
            boolean correctNumberCard = true;
            for (int i = 0; i < checkNumberCard.length(); i++) {
                if (!Character.isDigit(checkNumberCard.charAt(i))) {
                    correctNumberCard = false;
                }
            }
            if (checkNumberCard.length() != 16) correctNumberCard = false;
            if (!correctNumberCard)
                JOptionPane.showMessageDialog(null, "Invalid card number", "Error", JOptionPane.WARNING_MESSAGE);

            String checkCvcCard = textCvc.getText();
            boolean correctCvc = true;
            for (int i = 0; i < checkCvcCard.length(); i++) {
                if (!Character.isDigit(checkCvcCard.charAt(i))) {
                    correctCvc = false;
                }
            }
            if (checkCvcCard.length() != 3) correctCvc = false;
            if (!correctCvc)
                JOptionPane.showMessageDialog(null, "Invalid cvc number", "Error", JOptionPane.WARNING_MESSAGE);


            String checkDateCard = textDateCard.getText();
            boolean correctDateCard = true;

            SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
            sdf.setLenient(false);

            try {
                Date cardDate = sdf.parse(checkDateCard);
                Calendar current = Calendar.getInstance();
                current.set(Calendar.DAY_OF_MONTH, 1);
                correctDateCard = !cardDate.before(current.getTime());
            } catch (ParseException t) {
                correctDateCard = false;
            }
            if (!correctDateCard)
                JOptionPane.showMessageDialog(null, "Invalid date", "Error", JOptionPane.WARNING_MESSAGE);

            if (correctNumberCard && correctDateCard && correctCvc) {


                String card = textNumberCard.getText() + textDateCard.getText() + textCvc.getText();
                String encryptedData = null;
                if (checkBoxSave.isSelected()) {
                    
                    try {
                        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                        keyGenerator.init(128);
                        SecretKey secretKey = keyGenerator.generateKey();

                        Cipher cipher = Cipher.getInstance("AES");
                        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                        byte[] encryptedBytes = cipher.doFinal(card.getBytes(StandardCharsets.UTF_8));


                        encryptedData = Base64.getEncoder().encodeToString(encryptedBytes);


                        File file = new File("src/keys.txt");
                        try (FileWriter writer = new FileWriter(file, true)) {
                            writer.write((Payment.getIdPayment()+1) + " " + secretKey + System.lineSeparator());
                        }
                        if (reservationAround != null){
                            try (FileWriter writer = new FileWriter(file, true)) {
                                writer.write((Payment.getIdPayment()+2) + " " + secretKey + System.lineSeparator());
                            }
                        }

                    } catch (Exception r) {
                        r.printStackTrace();
                    }
                }
                System.out.println("statusReservation: "+statusReservation);
                    if (statusReservation) {

                        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String formattedDate2 = dateFormat2.format(new Date());
                        if (db.insert_row_payment(conn, new Payment(reservationOrigen.getPosition(), formattedDate2, encryptedData))) {
                            JOptionPane.showMessageDialog(null, "You have successfully purchased tickets!!!", "Congratulations", JOptionPane.PLAIN_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, "something went wrong... try again", "Error", JOptionPane.WARNING_MESSAGE);
                        this.dispose();


                    } else {
                        Reservation reservationOrigenToBase = new Reservation(reservationOrigen.getNumberOfTickets(), reservationOrigen.getIdFlight(), reservationOrigen.getDateReservation(), reservationOrigen.getIdUser(), reservationOrigen.getTotalPrice());
                        if (db.insert_row_reservation(conn, reservationOrigenToBase)) {
                            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String formattedDate2 = dateFormat2.format(new Date());
                            if (db.insert_row_payment(conn, new Payment(reservationOrigenToBase.getPosition(), formattedDate2, encryptedData))) {
                                JOptionPane.showMessageDialog(null, "You have successfully purchased tickets!!!", "Congratulations", JOptionPane.PLAIN_MESSAGE);
                            } else
                                JOptionPane.showMessageDialog(null, "something went wrong... try again", "Error", JOptionPane.WARNING_MESSAGE);
                            if (reservationAround != null) {
                                Reservation reservationDestinationToBase = new Reservation(reservationAround.getNumberOfTickets(), reservationAround.getIdFlight(), reservationAround.getDateReservation(), reservationAround.getIdUser(), reservationAround.getTotalPrice());
                                if (db.insert_row_reservation(conn, reservationDestinationToBase)) {
                                    if (!db.insert_row_payment(conn, new Payment(reservationDestinationToBase.getPosition(), formattedDate2, encryptedData))) {
                                        JOptionPane.showMessageDialog(null, "something went wrong... try again", "Error", JOptionPane.WARNING_MESSAGE);
                                    }
                                } else
                                    JOptionPane.showMessageDialog(null, "something went wrong... try again", "Error", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        this.dispose();
                    }
            }
        }
        if(e.getSource()==buttonBack){
            this.dispose();
        }
    }
}
