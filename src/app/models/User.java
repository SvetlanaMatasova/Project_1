package app.models;

import app.DbFunctions;

import java.sql.Connection;

public class User {
    private static int idUser;
    private String password;
    private String nameUser;
    private String email;
    private String phoneNumber;
    private double milesTravelled;
    private boolean statusManager;
    private static boolean isManager;
    private static boolean statusUser;
    private static String nameStaticUser;

    private int position;
    public User() {
    }
    public User(String nameUser, String email, String phoneNumber, String password, boolean statusManager) {
        idUser++;
        this.position=idUser;
        this.password = password;
        this.nameUser = nameUser;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.statusManager=statusManager;
        nameStaticUser=nameUser;
        statusUser=true;
        isManager=statusManager;
    }
    public User(int id, String password, String nameUser, String email, String phoneNumber, boolean statusManager) {
        idUser=id;
        this.password = password;
        this.nameUser = nameUser;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.statusManager=statusManager;
        nameStaticUser=nameUser;
        statusUser=true;
        isManager=statusManager;
    }

    public boolean isStatusManager() {
        return statusManager;
    }

    public int getPosition() {
        return position;
    }

    public static boolean isIsManager() {
        return isManager;
    }

    public static void setIsManager(boolean isManager) {
        User.isManager = isManager;
    }

    public void setStatusManager(boolean statusManager) {
        this.statusManager = statusManager;
    }

    public static String getNameStaticUser() {
        return nameStaticUser;
    }

    public static void setStatusUser(boolean statusUser) {
        User.statusUser = statusUser;
    }

    public static void setNameStaticUser(String nameStaticUser) {
        User.nameStaticUser = nameStaticUser;
    }

    public static boolean isStatusUser() {
        return statusUser;
    }

    public static int getIdUser() {
        return idUser;
    }

    public static void setIdUser(int idUser) {
        User.idUser = idUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getMilesTravelled() {
        return milesTravelled;
    }

    public void setMilesTravelled(double milesTravelled) {
        this.milesTravelled = milesTravelled;
    }

    @Override
    public String toString() {
        return "Users{" +
                "idUser=" + idUser +
                ", password='" + password + '\'' +
                ", nameUser='" + nameUser + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
