/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.Category;
import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author brayan
 */
public class CategoryRepository implements ICategoryRepository {

    public CategoryRepository() {
       
    }
    
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
            System.out.println("Error");
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
    public int save(Category newCategory) {
        int categoryId = 0;
        try {
            if (newCategory == null) {
                return categoryId;
            }
            this.connect();
            
            String sql = "{CALL InsertCategory(?, ?)}";
            CallableStatement cstmt = conn.prepareCall(sql);
            cstmt.setString(1, newCategory.getName());
            cstmt.registerOutParameter(2, Types.INTEGER); 
                
            cstmt.executeUpdate();         
            categoryId = cstmt.getInt(2);
            
            cstmt.close();      
            this.disconnect();
            return categoryId;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoryId;       
    }


    public boolean clearCategories() {
    try {
        this.connect();
        String sql = "DELETE FROM category";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();

        // Reset AUTOINCREMENT value for categoryId
        String resetSql = "UPDATE SQLITE_SEQUENCE SET seq = 0 WHERE name = 'category'";
        PreparedStatement pstmtReset = conn.prepareStatement(resetSql);
        pstmtReset.executeUpdate();
        this.disconnect();
        return true;
    } catch (SQLException ex) {
        Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}

    @Override
    public boolean edit(int id, Category category) {
        try {
            if (id <= 0 || category == null) {
                return false;
            }
            this.connect();
            String sql = "UPDATE category "
                    + "SET name=?"
                    + "WHERE categoryId=? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category.getName());
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }   
    return false;
}

    @Override
    public boolean delete(int id) {
        try {
            if (id <= 0) {
                return false;
            }
             this.connect();
             String sql = "DELETE FROM category "
                    + "WHERE categoryId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
            this.disconnect();
            return true;
        } catch (SQLException e) {
              Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
 
    @Override
    public Category findById(int id) {
    try {
        this.connect();
        String sql = "{CALL GetCategory(?)}";
        CallableStatement cstmt = conn.prepareCall(sql);
        cstmt.setInt(1, id); // Reemplaza el valor 2 con el valor deseado para el parámetro del procedimiento almacenado
        ResultSet res = cstmt.executeQuery();
        
        if (res.next()) {
            int categoryId = res.getInt("categoryId");
            String categoryName = res.getString("name");
            
            Category cat = new Category();
            cat.setCategoryId(categoryId);
            cat.setName(categoryName);
            
            this.disconnect();
            return cat;
        } else {
            return null;
        }
    } catch (SQLException ex) {
        Logger.getLogger(CategoryRepository.class.getName()).log(Level.SEVERE, "Error al consultar la categoría en la base de datos", ex);
    }
    return null;
}



    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try {
            this.connect();
            String sql = "SELECT * FROM category";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("categoryId");
                String name = rs.getString("name");
                Category category = new Category(id, name);
                categories.add(category);
            }
        } catch (SQLException e) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, e);
        }
        this.disconnect();
        return categories;
    }
  @Override
    public List<Category> findByName(String name) {
        List<Category> categories = new ArrayList<>();
        try {
            this.connect();
            String sql = "SELECT * FROM category WHERE name = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("categoryId");
                String categoryName = rs.getString("name");
                Category category = new Category(id, categoryName);
                categories.add(category);
            }
            
        } catch (Exception e) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, e);
        }
        this.disconnect();
        return categories;
    }
    

}
