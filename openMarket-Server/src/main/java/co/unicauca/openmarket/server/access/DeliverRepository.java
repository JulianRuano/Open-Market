/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.application.Invoice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brayan
 */
public class DeliverRepository implements IDeliverRepository{
    private Connection conn;
    public boolean connect() {
         try {
            String url = "jdbc:mysql://162.241.61.245:3306/codoslic_op?noAccessToProcedureBodies=true";
            Properties props = new Properties();
            props.setProperty("user", "codoslic_user");
            props.setProperty("password", "singlecode4");
            this.conn = DriverManager.getConnection(url, props);         
            
             System.out.println("Conexion exitosa a la base de datos");
            return conn != null;
        } catch (Exception e) {
            System.out.println("Error"+e);
            return false;
        }
    }
    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    

    @Override
    public boolean qualification(String reference, int puntuacion,int userID) {
        try {        
            this.connect();
            String sql = "UPDATE receipt "
                    + "SET qualification=? "
                    + "WHERE receiptId = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, puntuacion);
                pstmt.setString(2, reference);

                pstmt.executeUpdate();
                pstmt.close();
                this.disconnect();  
            }                           
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DeliverRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.disconnect();
        return false;      
    }

    
    public double confirm(int userID){
        double average = 0;
        try {
            String sql = "SELECT AVG(qualification) AS promedio_qualification" +
                   " FROM receipt" +
                   " WHERE userID = ?";
            try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setInt(1,userID);
                
                ResultSet res = pstmt.executeQuery();
                if (res.next()) {
                    average = res.getDouble("promedio_qualification");    
                }
                pstmt.close();
            }               
        }
        catch (SQLException ex) {
            Logger.getLogger(DeliverRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.disconnect();
        return average;  
    }

    
    @Override
    public List<Invoice> billList(int userID) {
        List<Invoice> invoce = new ArrayList<>();
        try{
                this.connect();
                String sql = "SELECT receipt.receiptID, product.name, product.price, receipt.creationDate, receipt.state " +
                            " FROM receipt" +
                            " JOIN tiene ON receipt.receiptID = tiene.receiptId" +
                            " JOIN product ON tiene.productId = product.productId" +
                            " WHERE receipt.state <> 'confirmado' AND receipt.userID = ?";
               PreparedStatement pstmt = conn.prepareStatement(sql);
               pstmt.setInt(1, userID);
               ResultSet res = pstmt.executeQuery();
                
               while (res.next()){
                   Invoice inv = new Invoice();
                    inv.setReference(res.getString("receipt.receiptID"));
                    inv.setNameProduct(res.getString("product.name"));
                    inv.setPrice(res.getDouble("product.price"));
                    inv.setFecha(res.getString("receipt.creationDate"));
                    inv.setStatus(res.getString("receipt.state"));
                    invoce.add(inv);                  
               }
                
                pstmt.close();
                this.disconnect();
                return invoce;
                        
        }
        catch (SQLException ex) {
           Logger.getLogger(DeliverRepository.class.getName()).log(Level.SEVERE, null, ex);
       }
        this.disconnect();
        return null;
    }

    @Override          
    public boolean UpdateBalance(String reference,int userID){
        try {
           this.connect();
           String resetSql = "UPDATE user " +
                                " SET money = money + (" +
                                "    SELECT SUM(price)" +
                                "    FROM receipt " +
                                "    INNER JOIN tiene ON receipt.receiptId = tiene.receiptId" +
                                "    INNER JOIN product ON tiene.productId = product.productId " +
                                "    WHERE receipt.receiptId = ?)*0.95" +
                                " WHERE userID = ?";

           PreparedStatement pstmtReset = conn.prepareStatement(resetSql);
           pstmtReset.setString(1, reference);
           pstmtReset.setInt(2, userID);
           pstmtReset.executeUpdate();
           pstmtReset.close();
           this.disconnect();
           return true;
       } catch (SQLException ex) {
           Logger.getLogger(DeliverRepository.class.getName()).log(Level.SEVERE, null, ex);
       }
        this.disconnect();
       return false;
    }
    
    @Override
    public double balance(int userID){
        double money = 0;
        try{           
            this.connect();
             String sql ="SELECT money FROM user " +
                         "where userID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql); 
            pstmt.setInt(1, userID);
            ResultSet res = pstmt.executeQuery();
            
            if (res.next()) {
                 money = res.getDouble("money");
            }
            
            pstmt.close();           
            this.disconnect();
            return money;           
        }
         catch (SQLException ex) {
           Logger.getLogger(DeliverRepository.class.getName()).log(Level.SEVERE, null, ex);
       }
       this.disconnect();
       return money; 
    }  
}
