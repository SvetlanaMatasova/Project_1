package GUI;

import app.DbFunctions;
import app.models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameLogIn extends JFrame implements ActionListener {
    JLabel labelImage,labelPicker,labelName,labelPassword;
    JPanel panelImage, panelUser, panelEnter,panelName, panelPassword;
    JComboBox comboBoxOrigen, comboBoxDestination;
    JButton buttonEnter, buttonBack;
    JTextField textName,textPassword;
   DbFunctions db;
    java.sql.Connection conn;

    public FrameLogIn(java.sql.Connection conn){
        db =new DbFunctions();
        this.conn= conn;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900,  635);
        this.setLayout(new BorderLayout());

        buttonEnter = new JButton("Enter");
        buttonEnter.setPreferredSize(new Dimension(100,40));
        buttonEnter.addActionListener(this);

        buttonBack = new JButton("Back");
        buttonBack.setPreferredSize(new Dimension(100,40));
        buttonBack.addActionListener(this);

        panelImage=new JPanel();
        panelUser = new JPanel();
        panelEnter = new JPanel();
        labelImage = new JLabel();
        labelName= new JLabel();
        labelPassword= new JLabel();
        panelPassword= new JPanel();
        panelName= new JPanel();
        textName = new JTextField();
        textPassword = new JTextField();

        textName.setPreferredSize(new Dimension(250,30));
        textPassword.setPreferredSize(new Dimension(250,30));

        labelName.setText("Login:      ");
        labelPassword.setText("Password:");

        panelName.setLayout(new FlowLayout());
        panelPassword.setLayout(new FlowLayout());
        panelName.add(labelName);
        panelName.add(textName);
        panelPassword.add(labelPassword);
        panelPassword.add(textPassword);

        panelImage.setPreferredSize(new Dimension(100,300));
        panelUser.setPreferredSize(new Dimension(500,100));
        panelEnter.setPreferredSize(new Dimension(100,200));

        panelImage.setBackground(Color.white);
        panelUser.setBackground(Color.white);
        panelEnter.setBackground(Color.white);
        labelImage.setIcon(new ImageIcon("src/GUI/banner.jpg"));
        panelImage.add(labelImage);
        panelName.setBackground(Color.white);
        panelPassword.setBackground(Color.white);

        panelUser.add(buttonBack);
        panelUser.add(buttonEnter);
        panelUser.setLayout(null);
        buttonBack.setBounds(750,0,100,30);
        buttonEnter.setBounds(150,0,100,30);

        panelEnter.setLayout(null);
        panelName.setBounds(50,50,400,50);
        panelPassword.setBounds(50,100,400,50);
        panelEnter.add(panelName);
        panelEnter.add(panelPassword);

        this.add(panelUser,BorderLayout.SOUTH);
        this.add(panelEnter,BorderLayout.CENTER);
        this.add(panelImage,BorderLayout.NORTH);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonEnter){
            db.search_by_user((java.sql.Connection) conn,textName.getText(),textPassword.getText());
            if(User.isStatusUser()){
                this.dispose();
               new MyFrame(conn);
            } else JOptionPane.showMessageDialog(null,"This user was not found, change your details or register","Error",JOptionPane.WARNING_MESSAGE);
        }
        if(e.getSource()==buttonBack){
            this.dispose();
            new MyFrame(conn);
        }
    }
}
