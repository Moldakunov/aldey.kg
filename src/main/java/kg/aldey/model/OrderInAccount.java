package kg.aldey.model;

import java.util.ArrayList;

public class OrderInAccount {

    private int id;
    private String date;
    private String clientPhone;
    private String closed;
    private double price;
    private double discount;
    private double payment;
    private int storeId;
    private double balance;
    private String adress;
    private String storeName;
    private String storePhone;
    private String storeMail;
    private String storeAdress;
    private ArrayList<Payment> paymentList;
    private ArrayList<ProductInOrder> journalList;

    public OrderInAccount() {
    }

    public OrderInAccount(int id, String date, String clientPhone, String closed, double price, double discount, double payment, int storeId, double balance, String adress, String storeName, String storePhone, String storeMail, String storeAdress, ArrayList<Payment> paymentList, ArrayList<ProductInOrder> journalList) {
        this.id = id;
        this.date = date;
        this.clientPhone = clientPhone;
        this.closed = closed;
        this.price = price;
        this.discount = discount;
        this.payment = payment;
        this.storeId = storeId;
        this.balance = balance;
        this.adress = adress;
        this.storeName = storeName;
        this.storePhone = storePhone;
        this.storeMail = storeMail;
        this.storeAdress = storeAdress;
        this.paymentList = paymentList;
        this.journalList = journalList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public String getStoreMail() {
        return storeMail;
    }

    public void setStoreMail(String storeMail) {
        this.storeMail = storeMail;
    }

    public String getStoreAdress() {
        return storeAdress;
    }

    public void setStoreAdress(String storeAdress) {
        this.storeAdress = storeAdress;
    }

    public ArrayList<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(ArrayList<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public ArrayList<ProductInOrder> getJournalList() {
        return journalList;
    }

    public void setJournalList(ArrayList<ProductInOrder> journalList) {
        this.journalList = journalList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
                ", closed='" + closed + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", payment=" + payment +
                ", storeId=" + storeId +
                ", balance=" + balance +
                ", adress='" + adress + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storePhone='" + storePhone + '\'' +
                ", storeMail='" + storeMail + '\'' +
                ", storeAdress='" + storeAdress + '\'' +
                ", paymentList=" + paymentList +
                ", journalList=" + journalList +
                '}';
    }
}
