/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;

/**
 *
 * @author Hernah
 */
public class Employee {
    private String orgusername;
    private String username;
    private String password;
    private String restrictionlvl;
    private boolean status;

    public Employee(String orgusername, String username, String password, String restrictionlvl, boolean status) {
        this.orgusername = orgusername;
        this.username = username;
        this.password = password;
        this.restrictionlvl = restrictionlvl;
        this.status = status;
    }

    public Employee(String orgusername, String username, String restrictionlvl, boolean status) {
        this.orgusername = orgusername;
        this.username = username;
        this.restrictionlvl = restrictionlvl;
        this.status = status;
    }

    public String getOrgusername() {
        return orgusername;
    }

    public void setOrgusername(String orgusername) {
        this.orgusername = orgusername;
    }   
    

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public String getRestrictionlvl() {
        return restrictionlvl;
    }

    public void setRestrictionlvl(String restrictionlvl) {
        this.restrictionlvl = restrictionlvl;
    }
    
    
}
