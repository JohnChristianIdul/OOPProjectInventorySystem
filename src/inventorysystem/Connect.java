/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Hernah
 */
public class Connect {
    Connection conn;
    public Connect() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbinventorysystem", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int checkRestrictionLvl(String username) {
        String sql = "select restrictionlevel from organization where employeeusername='" + username + "'";
        Statement stmt;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()) {
                String acctype = rs.getString("restrictionlevel");
                if(acctype.equalsIgnoreCase("No restrictions")) {
                    return 0;
                } else if(acctype.equalsIgnoreCase("Can update only")) {
                    return 1;
                } else if(acctype.equalsIgnoreCase("Read only")) {
                    return 2;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int login(String username, String password){
        Statement stmt;
        String sql;
        ResultSet rs;
        try {
           stmt=conn.createStatement();
           sql ="select * from account where username='"+username+"' and password='"+password+"'";
           rs = stmt.executeQuery(sql);
           if(rs.next()) {
               String pass = rs.getString("password");
               sql = "select accounttype from account where username='" + username +"'";
               rs = stmt.executeQuery(sql);
               if(rs.next()) {
                   String acctype = rs.getString("accounttype");
                    if(pass.equalsIgnoreCase("default")) {
                        return 5;
                    } else if(acctype.equalsIgnoreCase("FinanceDept")) {
                        return 1;
                    } else if(acctype.equalsIgnoreCase("Organization")) {
                        return 2;
                    } else if(acctype.equalsIgnoreCase("Entrepreneur")) {
                        return 3;
                    } else if(acctype.equalsIgnoreCase("Employee")) {
                        return 4;
                    } 
               } 
           } else {
               return 0;
           }

        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public ResultSet displayVerification(){
        String sql ="select transactionid, username, amount, verification.subscriberid, subscriber.balance, subscriber.subscriptiontype, transactiontype, status from verification, subscriber where verification.subscriberid=subscriber.subscriberid";
        Statement stmt;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public ResultSet displayVerificationFiltered(int status) {
        ResultSet rs;
        Statement stmt;
        String sql ="select transactionid, username, amount, verification.subscriberid, subscriber.balance, subscriber.subscriptiontype, transactiontype, status from verification, subscriber where verification.subscriberid=subscriber.subscriberid AND status=" + status;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void approveTransaction(int transactionID){
        String sql ="select amount, subscriberid, transactiontype from verification where transactionid="+transactionID;
        Statement stmt;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            int subID;
            double amount;
            if(rs.next()) {
                subID = rs.getInt("subscriberid");
                amount = rs.getDouble("amount");
                String transaction = rs.getString("transactiontype");
                sql = "select subscriptionperiod, balance from subscriber where subscriberid=" + subID;
                rs = stmt.executeQuery(sql);
                if(rs.next()) {
                    int subscriptionperiod;
                    double balance = rs.getDouble("balance");
                    if(transaction.equalsIgnoreCase("subscribe")) {
                        subscriptionperiod = rs.getInt("subscriptionperiod") + 28;
                        balance = balance - amount;
                        sql = "update subscriber set subscriptiontype='Corporate', subscriptionperiod=" + subscriptionperiod +", balance=" +balance+ " where subscriberid=" +subID;
                    } else if(transaction.equalsIgnoreCase("unsubscribe")) {
                        subscriptionperiod = -1;
                        sql = "update subscriber set subscriptiontype='Basic', subscriptionperiod=" + subscriptionperiod +" where subscriberid=" +subID;
                    }
                    stmt.executeUpdate(sql);
                    sql = "update verification set status=1 where transactionid=" +transactionID;
                    stmt.executeUpdate(sql);
                    }
                }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void rejectTransaction(int transactionID) {
        String sql ="update verification set status=-1 where transactionid="+transactionID;
        Statement stmt;
        
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }    }
    
        
    public ResultSet displayOrganization(String username) {
        String sql = "select * from organization where username='" + username +"'";
        Statement stmt;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int addEmployee(Employee e) {
        String sql = "select * from organization where employeeusername='" + e.getUsername() +"'";
        Statement stmt;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()==false) {
                sql = "insert into organization(username,employeeusername,restrictionlevel,employeestatus) values ('"+ e.getOrgusername() + "','" + e.getUsername() + "','" + e.getRestrictionlvl() + "'," + e.isStatus() + ")";
                stmt.execute(sql);
                if(e.isStatus() == true) {
                    sql = "insert into account values('" + e.getUsername() + "','default','Employee')";
                    stmt.execute(sql);
                }
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public void updatePassword(String newPass, String username) {
        String sql = "update account set password='" + newPass +"' where username='" +username + "'";
        Statement stmt;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void updateEmployee(Employee e, String empUsernamePrev, String username) {
        String sql = "update organization set employeeusername='" + e.getUsername()+"', restrictionlevel='" + e.getRestrictionlvl() +"', employeestatus=" + e.isStatus() + " where employeeusername='" +empUsernamePrev + "'" ;
        Statement stmt;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            if(!e.isStatus()) {
                JOptionPane.showMessageDialog(null,"The employee no longer has access to the inventory","Removed inventory access",JOptionPane.WARNING_MESSAGE);
                sql = "delete from account where username='" + empUsernamePrev +"'";
                stmt.execute(sql);
            } else {
                sql = "update account set username='" + e.getUsername() + "' where username='"+ empUsernamePrev +"'";
                stmt.execute(sql);
                sql = "select * from account where username='" + e.getUsername() + "'";
                rs = stmt.executeQuery(sql);
                if(rs.next()==false) {
                    sql = "insert into account values('" + e.getUsername() + "','default','Employee')";
                    stmt.execute(sql);
                }
            }   
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet displayOrgFiltered(boolean status) {
        String sql = "select * from organization where employeeStatus=" + status;
        Statement stmt;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ResultSet checkSubscriber(int subscriberID) {
        Statement stmt;
        String sql;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            sql = "SELECT * from subscriber where subscriberID = "+subscriberID+"";
            rs = stmt.executeQuery(sql);
            if(rs.next()) return rs;
        } catch(SQLException ss) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null,ss);
        }
        return null;
    }
    
    public boolean createInventory(int subscriberID, String inventoryName) {
        Statement stmt;
        String sql;
        ResultSet rs = null, rs2 = null;
        int count = 1;
        int check;
        
        if(checkInventoryName(subscriberID, inventoryName)) {
            JOptionPane.showMessageDialog(null, "Inventory exists!");
            return false;
        }
        
        try {
            stmt = conn.createStatement();
            sql = "SELECT subscriptionType from subscriber where subscriberID = "+subscriberID+"";
            rs = stmt.executeQuery(sql);
            rs2 = checkInventoryID();
            if(rs2 != null) count = rs2.getInt("inventoryID") + 1;
            while(rs.next()) {
            if(rs.getString("subscriptionType").equalsIgnoreCase("Corporate")) {
                sql = "INSERT INTO inventory VALUES("+count+", '"+inventoryName+"', "+subscriberID+")";
                stmt.executeUpdate(sql);
                //sql = "CREATE TABLE " + inventoryName + " (" +"itemID INT(3) PRIMARY KEY AUTO_INCREMENT," +"itemName VARCHAR(20) NOT NULL," +"description VARCHAR(20) NOT NULL," +"itemQuantity INT(3) NOT NULL," +"inventoryID INT(3) NOT NULL," +"isVisible TINYINT(1) NOT NULL" +")";
                //stmt.executeUpdate(sql);
                return true;
            } else {
                check = checkCapacity(subscriberID);
                if(check < 20) {
                    sql = "INSERT INTO inventory VALUES("+count+", '"+inventoryName+"', "+subscriberID+")";
                    stmt.executeUpdate(sql);
                    return true;
                } else JOptionPane.showMessageDialog(null, "Out of capacity!");
            }
            }
        } catch(SQLException s) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null,s);
        }
        return false;
    }
    
    public ResultSet checkInventoryID() {
        Statement stmt;
        String sql;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            sql = "SELECT inventoryID from inventory order by inventoryID DESC";
            rs = stmt.executeQuery(sql);
            if(rs.next()) return rs;
        } catch(SQLException ss) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null,ss);
        }
        return null;
    }
    
    public boolean checkInventoryName(int subscriberID, String inventoryName) {
        Statement stmt;
        String sql;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            sql = "select inventoryName from inventory where subscriberID = "+subscriberID+"";
            rs = stmt.executeQuery(sql);
            while(rs.next()) { 
                if(rs.getString("inventoryName").equalsIgnoreCase(inventoryName)) return true;
            }   
        } catch(SQLException ss) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null,ss);
        }
        return false;
    }
    
    public int checkCapacity(int subscriberID) {
        Statement stmt;
        String sql;
        ResultSet rs = null;
        int num = 0;
        try {
            stmt = conn.createStatement();
            sql = "select count(inventoryID) as numOfInventories from inventory where subscriberID = "+subscriberID+"";
            rs = stmt.executeQuery(sql);
            while(rs.next()) { 
                num = rs.getInt("numOfInventories");
            }   
        } catch(SQLException ss) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null,ss);
        }
        return num;
    }
    
    public ArrayList<Item> displayInventory(int inventoryID) {
        ArrayList<Item> acc = new ArrayList<Item>();
        String sql ="select itemID, itemName, itemDescription, itemQuantity, itemStatus from item where inventoryID = '"+inventoryID+"'";
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
              Item a = new Item(rs.getInt("itemID"), rs.getString("itemName"), rs.getString("itemDescription"), rs.getInt("itemQuantity"), rs.getBoolean("itemStatus")); 
              acc.add(a);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return acc;
    }
    
    public int getInventoryID(int subscriberID, String inventoryName){
        Statement stmt;
        String sql;
        ResultSet rs = null;
        int num = 0;
        try {
            stmt = conn.createStatement();
            sql = "select inventoryID from inventory where subscriberID = "+subscriberID+" and inventoryName = '"+inventoryName+"'";
            rs = stmt.executeQuery(sql);
            while(rs.next()) { 
                num = rs.getInt("inventoryID");
            }   
        } catch(SQLException ss) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null,ss);
        }
        return num;
    }
}
