
package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brayan
 */
public class UserRepository implements IUserRepository{
    private Connection conn;
    private final String bd = "openmarket";
    private final String user = "codoslic_user";
    private final String password = "singlecode4";
   
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
    public User login(User user) {
      try{
          this.connect();
          String sql = "SELECT userID,userName FROM user "
           + "WHERE userName LIKE ? AND password LIKE ?";
          PreparedStatement pstmt = conn.prepareStatement(sql);
          pstmt.setString(1,user.getUsername());
          pstmt.setString(2,user.getContrasenia());
          ResultSet res = pstmt.executeQuery();
          if (res.next()){
              User newUser=new User();
              newUser.setUsername(res.getString("userName"));
              newUser.setUserId(res.getInt("userID"));
              pstmt.close();
              this.disconnect(); 
              return newUser;
          }
      }catch(SQLException ex){
           Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
      }
        this.disconnect();
        return null;
    }

    @Override
    public boolean register(User user) {
        try{
            if(user==null){
                return false;
            }
            this.connect();
            String sql="INSERT INTO user(name,lastName,rol,email,userName,password)VALUES(?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,user.getFirstName());
            pstmt.setString(2,user.getLastName());
            pstmt.setString(3,user.getRol());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5,user.getUsername());
            pstmt.setString(6,user.getContrasenia());
            pstmt.executeUpdate(); 
            pstmt.close();
            this.disconnect();
            return true;
        }catch(Exception ex){
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
         disconnect();
         return false;
    }
    
      
    
}
