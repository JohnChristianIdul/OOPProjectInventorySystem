/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;

/**
 *
 * @author Hernah
 */
public class Verification {
    private int transactionID;
    private String username;
    private double amount;
    private double payable;
    private String transactionType;
    private int subscriptionPeriod;
    private String subscriptionType;
    private int status;

    public Verification(int transactionID, String username, double amount, double payable, String transactionType, int subscriptionPeriod, String subscriptionType, int status) {
        this.transactionID = transactionID;
        this.username = username;
        this.amount = amount;
        this.payable = payable;
        this.transactionType = transactionType;
        this.subscriptionPeriod = subscriptionPeriod;
        this.subscriptionType = subscriptionType;
        this.status = status;
    }

    public Verification(String username, double amount, double payable, String transactionType, int subscriptionPeriod, String subscriptionType, int status) {
        this.username = username;
        this.amount = amount;
        this.payable = payable;
        this.transactionType = transactionType;
        this.subscriptionPeriod = subscriptionPeriod;
        this.subscriptionType = subscriptionType;
        this.status = status;
    }
    
    
    public Verification() {
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPayable() {
        return payable;
    }

    public void setPayable(double payable) {
        this.payable = payable;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSubscriptionPeriod() {
        return subscriptionPeriod;
    }

    public void setSubscriptionPeriod(int subscriptionPeriod) {
        this.subscriptionPeriod = subscriptionPeriod;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }
    
    
}
