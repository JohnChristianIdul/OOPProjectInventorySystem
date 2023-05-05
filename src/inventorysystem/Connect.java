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
    
    public int login(String username, String password){
        Statement stmt;
        String sql;
        ResultSet rs;
        try {
            stmt=conn.createStatement();
            sql ="select * from account where username='"+username+"' and password='"+password+"'";
            rs = stmt.executeQuery(sql);
            if (rs.next()==true){
                sql ="select * from account where username='"+username+"' and accounttype='FinanceDept'";
                rs = stmt.executeQuery(sql);
                if (rs.next()==true) {
                   return 1; 
                } else {
                    sql ="select * from account where username='"+username+"' and accounttype='Organization'";
                    rs = stmt.executeQuery(sql);
                    if (rs.next()==true) {
                        return 2; 
                    } else {
                        sql ="select * from account where username='"+username+"' and accounttype='Entrepreneur'";
                        rs = stmt.executeQuery(sql);
                        if (rs.next()==true) {
                            return 3;
                        }
                    }
                }
                
            }  
            else
                return 0;
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public ArrayList<Verification> displayVerification(){
        ArrayList<Verification> verification = new ArrayList<Verification>();
        String sql ="select * from verification";
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
              Subscriber s = new Subscriber(rs.getInt(5),rs.getString(6),rs.getDouble(4));
              Verification v = new Verification(rs.getInt(1), rs.getString(2),rs.getDouble(3), s, rs.getString(7),rs.getInt(8)) ;
              verification.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return verification;
    }
    
    public void approveTransaction(int transactionID){
        String sql ="update verification set status=1 where transactionid="+transactionID;
        Statement stmt;
        
        try {
            stmt = conn.createStatement();
            stmt.execute(sql);
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
    
    
}
