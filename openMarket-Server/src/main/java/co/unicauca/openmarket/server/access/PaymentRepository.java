package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.application.Invoice;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
    public String save(String receiptId, String details) {
        try {
            this.connect();
            String sql = "{CALL InsertReceipt(?, ?, ?)}";
                  
            CallableStatement  pstmt = conn.prepareCall(sql);
            pstmt.setString(1, receiptId);
            pstmt.setString(2, details);
            pstmt.registerOutParameter(3, Types.INTEGER);
            pstmt.executeUpdate();
            
            Timestamp  createDate = pstmt.getTimestamp(3);                   
            String formato = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            String fechaString = sdf.format(createDate);
            
            pstmt.close();
            
            this.disconnect();
            return fechaString;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "NONE_BD";     
    }
  
    @Override
    public Invoice findById(String reference) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Double findPrice(String reference) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

}
