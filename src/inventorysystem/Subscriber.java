/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;

/**
 *
 * @author daugd
 */
public class Subscriber {
    private int subscriberID;
    private String subscribertype; 
    private double payable; //temporary only just to know subscriber payable
    private String restrictions;
    private boolean readOnly;

    public Subscriber(int subscriberID, String subscribertype, double payable) {
        this.subscriberID = subscriberID;
        this.subscribertype = subscribertype;
        this.payable = payable;
    }

    public String getSubscribertype() {
        return subscribertype;
    }

    public void setSubscribertype(String subscribertype) {
        this.subscribertype = subscribertype;
    }

    public double getPayable() {
        return payable;
    }

    public void setPayable(double payable) {
        this.payable = payable;
    }

    public Subscriber(int subscriberID, String restrictions, boolean readOnly) {
        this.subscriberID = subscriberID;
        this.restrictions = restrictions;
        this.readOnly = readOnly;
    }

    public Subscriber(int subscriberID) {
        this.subscriberID = subscriberID;
    }

    public int getSubscriberID() {
        return subscriberID;
    }

    public void setSubscriberID(int subscriberID) {
        this.subscriberID = subscriberID;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
}
