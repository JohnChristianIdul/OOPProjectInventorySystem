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
        String sql ="select transactionid, username, amount, verification.subscriberid, subscriber.balance, subscription.subscriptiontype, transactiontype, status from verification, subscription, subscriber where verification.subscriberid=subscriber.subscriberid AND subscriber.subscriptiontype=subscription.subscriptiontype";
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
        String sql ="select transactionid, username, amount, verification.subscriberid, subscriber.balance, subscription.subscriptiontype, transactiontype, status from verification, subscription, subscriber where verification.subscriberid=subscriber.subscriberid AND subscriber.subscriptiontype=subscription.subscriptiontype AND status=" + status;
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
}
