package app.models;

public class Payment {
    private static int idPayment;
    private int idReservation;
    private int position;
    private String dateOfPay;
    private String creditCardNumber;

    public Payment(int idReservation, String dateOfPayment, String creditCardNumber) {
        idPayment++;
        position=idPayment;
        this.idReservation = idReservation;
        this.dateOfPay = dateOfPayment;
        this.creditCardNumber = creditCardNumber;
    }

    public int getPosition() {
        return position;
    }

    public static int getIdPayment() {
        return idPayment;
    }

    public static void setIdPayment(int idPayment) {
        Payment.idPayment = idPayment;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public String getDateOfPay() {
        return dateOfPay;
    }

    public void setDateOfPay(String dateOfPay) {
        this.dateOfPay = dateOfPay;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @Override
    public String toString() {
        return "Payments{" +
                "idPayment=" + idPayment +
                ", idReservation=" + idReservation +
                ", dateOfPayment='" + dateOfPay + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                '}';
    }
}
