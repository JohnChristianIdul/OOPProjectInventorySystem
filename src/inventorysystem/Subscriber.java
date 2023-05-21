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
    private int subscriptionID;
    private String subscriptionType;
    private int subscriptionPeriod;
    private String username;

    public Subscriber() {
        setSubscriptionType("");
        setUsername("");
    }

    public Subscriber(String subscriptionType, int subscriptionPeriod, String username) {
        this(subscriptionType, subscriptionPeriod);
        setUsername(username);
    }
    
    
    public Subscriber(String subscriptionType, int subscriptionPeriod) {
        setSubscriptionType(subscriptionType);
        setSubscriptionPeriod(subscriptionPeriod);
    }

    public int getSubscriptionID() {
        return subscriptionID;
    }

    public void setSubscriptionID(int subscriptionID) {
        this.subscriptionID = subscriptionID;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public int getSubscriptionPeriod() {
        return subscriptionPeriod;
    }

    public void setSubscriptionPeriod(int subscriptionPeriod) {
        this.subscriptionPeriod = subscriptionPeriod;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
