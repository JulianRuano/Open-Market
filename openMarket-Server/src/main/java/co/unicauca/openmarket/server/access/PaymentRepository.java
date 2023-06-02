package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.application.Invoice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julian Ruano
 */
public class PaymentRepository implements IPaymentRepository{
    
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
    public boolean save(String receiptId, String details, int userID) {
        try {
            this.connect();
            String sql = "INSERT INTO receipt (receiptId,details) " +
                         "VALUES (?,?,?)";
                  
            PreparedStatement  pstmt = conn.prepareCall(sql);
            pstmt.setString(1, receiptId);
            pstmt.setString(2, details);
            pstmt.setInt(2, userID);

            pstmt.executeUpdate();
            pstmt.close();           
            this.disconnect();
            return true;
        } catch (SQLException ex) {
             Logger.getLogger(PaymentRepository.class.getName()).log(Level.SEVERE, "Error al consultar PaymentRepository de la base de datos", ex);
        }
        return false;     
    }
    
    
  
    @Override
    public Invoice findById(String reference) {
         try {
            this.connect();
            String sql = "SELECT tiene.receiptId, name, price, creationDate, state " +
                            "FROM  tiene " +
                            "INNER JOIN receipt ON receipt.receiptId = tiene.receiptId " +
                            "INNER JOIN product ON tiene.productId = product.productId " +
                            "WHERE receipt.receiptId = ? ";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, reference);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                Invoice inv = new Invoice();
                inv.setReference(res.getString("receiptId"));
                inv.setNameProduct(res.getString("name"));
                inv.setPrice(res.getDouble("price"));
                inv.setFecha(res.getString("creationDate"));
                inv.setStatus(res.getString("state"));
              
                pstmt.close();
                this.disconnect();
                return inv;
            } else {
                return null;
            }

        } catch (SQLException ex) {
             Logger.getLogger(PaymentRepository.class.getName()).log(Level.SEVERE, "Error al consultar PaymentRepositoryde la base de datos", ex);
        }
        return null;
    }

    @Override
    public Double findPrice(String reference) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean linkProduct(String receiptId, int productId) {
       try {
            this.connect();
            String sql = "INSERT INTO tiene (receiptId, productId)" +
                            "VALUES (?, ?,?)" ;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, receiptId);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();
            pstmt.close();
            this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PaymentRepository.class.getName()).log(Level.SEVERE, null, ex);
        }   
    return false;
    }

    

}
