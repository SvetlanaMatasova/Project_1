package GUI;

import app.DbFunctions;
import app.models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.util.Objects;

public class FrameRegistration extends JFrame implements ActionListener {
    JLabel labelImage,labelName,labelPassword, labelEmail, labelPhone;
    JPanel panelImage, panelUser, panelEnter,panelName, panelPassword, panelEmail, panelPhone;
    JButton buttonEnter, buttonBack;
    JTextField textName,textPassword, textEmail, textPhone;
    DbFunctions db;
    Connection conn;
    public FrameRegistration(Connection conn){
        db =new DbFunctions();
        this.conn= conn;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900,  700);
        this.setLayout(new BorderLayout());

        buttonEnter = new JButton("Registration");
        buttonEnter.setPreferredSize(new Dimension(120,40));
        buttonEnter.addActionListener(this);

        buttonBack = new JButton("Back");
        buttonBack.setPreferredSize(new Dimension(120,40));
        buttonBack.addActionListener(this);

        panelImage=new JPanel();
        panelUser = new JPanel();
        panelEnter = new JPanel();
        panelPassword= new JPanel();
        panelName= new JPanel();
        panelEmail= new JPanel();
        panelPhone = new JPanel();

        labelImage = new JLabel();
        labelName= new JLabel();
        labelPassword= new JLabel();
        labelEmail= new JLabel();
        labelPhone= new JLabel();

        textName = new JTextField();
        textPassword = new JTextField();
        textPhone= new JTextField();
        textEmail= new JTextField();

        textName.setPreferredSize(new Dimension(250,30));
        textPassword.setPreferredSize(new Dimension(250,30));
        textEmail.setPreferredSize(new Dimension(250,30));
        textPhone.setPreferredSize(new Dimension(250,30));

        labelName.setText("Login*:      ");
        labelPassword.setText("Password*:");
        labelEmail.setText("Email:        ");
        labelPhone.setText("Phone:       ");

        panelName.setLayout(new FlowLayout());
        panelPassword.setLayout(new FlowLayout());
        panelEmail.setLayout(new FlowLayout());
        panelPhone.setLayout(new FlowLayout());
        panelName.add(labelName);
        panelName.add(textName);
        panelPassword.add(labelPassword);
        panelPassword.add(textPassword);
        panelEmail.add(labelEmail);
        panelEmail.add(textEmail);
        panelPhone.add(labelPhone);
        panelPhone.add(textPhone);

        panelImage.setPreferredSize(new Dimension(100,300));
        panelUser.setPreferredSize(new Dimension(500,100));
        panelEnter.setPreferredSize(new Dimension(100,400));

        panelImage.setBackground(Color.white);
        panelUser.setBackground(Color.white);
        panelEnter.setBackground(Color.white);
        labelImage.setIcon(new ImageIcon("src/GUI/banner.jpg"));
        panelImage.add(labelImage);
        panelName.setBackground(Color.white);
        panelPassword.setBackground(Color.white);
        panelEmail.setBackground(Color.white);
        panelPhone.setBackground(Color.white);

        panelUser.add(buttonBack);
        panelUser.add(buttonEnter);
        panelUser.setLayout(null);
        buttonBack.setBounds(730,0,120,30);
        buttonEnter.setBounds(600,0,120,30);

        panelEnter.setLayout(null);
        panelName.setBounds(50,20,400,50);
        panelEmail.setBounds(50,70,400,50);
        panelPhone.setBounds(50,120,400,50);
        panelPassword.setBounds(50,170,400,50);


        panelEnter.add(panelName);
        panelEnter.add(panelEmail);
        panelEnter.add(panelPhone);
        panelEnter.add(panelPassword);

        this.add(panelUser,BorderLayout.SOUTH);
        this.add(panelEnter,BorderLayout.CENTER);
        this.add(panelImage,BorderLayout.NORTH);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonEnter){
            if((Objects.equals(textName.getText(), "")) || (textName.getText() == null) || (textPassword.getText().equals("")) || (textPassword.getText() == null)){
                JOptionPane.showMessageDialog(null,"Fields with (*) are required","Error",JOptionPane.WARNING_MESSAGE);
            }else{
                if(!db.search_by_user((Connection) conn, textName.getText())){
                    try {
                        db.insert_row_user(conn, new User(textName.getText(),textEmail.getText(), textPhone.getText(),textPassword.getText(),false));
                        this.dispose();
                        new MyFrame(conn);
                    }catch (Exception p){
                        JOptionPane.showMessageDialog(null,"something went wrong... try again","Error",JOptionPane.WARNING_MESSAGE);
                    }
                }else JOptionPane.showMessageDialog(null,"A user with the same name already exists... try again","Error",JOptionPane.WARNING_MESSAGE);
            }
          }
        if(e.getSource()==buttonBack){
            this.dispose();
            new MyFrame(conn);
        }
    }
}
