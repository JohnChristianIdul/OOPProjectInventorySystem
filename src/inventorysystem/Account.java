/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;

/**
 *
 * @author Lance Salera
 */
public class Account {
    private String username;
    private String password;
    private String accountType;

    public Account() {
        setUsername("");
        setPassword("");
        setAccountType("");
    }

    public Account(String username, String password, String accountType) {
        setUsername(username);
        setPassword(password);
        setAccountType(accountType);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    
    
}
