package kg.aldey.model;

public class Payment {
    private String id;
    private double sum;
    private String docNumber;
    private String date;
    private int typePay;

    public Payment() {
    }

    public Payment(String id, double sum, String docNumber, String date, int typePay) {
        this.id = id;
        this.sum = sum;
        this.docNumber = docNumber;
        this.date = date;
        this.typePay = typePay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTypePay() {
        return typePay;
    }

    public void setTypePay(int typePay) {
        this.typePay = typePay;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", sum=" + sum +
                ", docNumber='" + docNumber + '\'' +
                ", date='" + date + '\'' +
                ", typePay=" + typePay +
                '}';
    }
}
