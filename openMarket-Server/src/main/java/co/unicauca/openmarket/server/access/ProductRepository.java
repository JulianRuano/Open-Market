package co.unicauca.openmarket.server.access;


import co.unicauca.openmarket.commons.domain.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class ProductRepository implements IProductRepository {

    private Connection conn;
    private final String bd = "openmarket";
    private final String user = "root";
    private final String password = "";

    public ProductRepository() {
        
    }
    
    public boolean connect() {
         try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn  = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + bd, user, password);
            return conn != null;
        } catch (Exception e) {
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
    public boolean save(Product newProduct) {

        try {
            //Validate product
            if (newProduct == null || newProduct.getName().isBlank()) {
                return false;
            }
            this.connect();

            String sql = "INSERT INTO product (ProductId,name,description,price,address,image,categoryId) "
                    + "VALUES ( ?, ?, ?, ? , ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setLong(1, newProduct.getProductId());
                pstmt.setString(2, newProduct.getName());
                pstmt.setString(3, newProduct.getDescription());
                pstmt.setDouble(4, newProduct.getPrice());
                pstmt.setString(5, newProduct.getAddress());
                pstmt.setBytes(6, newProduct.getImage());
                pstmt.setLong(7, newProduct.getCategoryId());
                pstmt.executeUpdate();
                pstmt.close();
                this.disconnect();
                return true;  
            }
                          
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
        return false;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {

            String sql = "SELECT * FROM product";
            this.connect();

            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                Product newProduct = new Product();
                newProduct.setProductId(res.getLong("ProductId"));
                newProduct.setName(res.getString("name"));
                newProduct.setDescription(res.getString("description"));
                newProduct.setPrice(res.getDouble("price"));
                newProduct.setAddress(res.getString("address"));
                newProduct.setCategoryId(res.getLong("categoryId"));
                newProduct.setImage(res.getBytes("image"));
                products.add(newProduct);
            }
            this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    

    

    @Override
    public boolean edit(Product product) {
        try {
            //Validate product
            if (product.getProductId()<= 0 || product == null) {
                return false;
            }
            this.connect();

            String sql = "UPDATE  product "
                    + "SET name=?, description=?, price=?, address=?, categoryId=?, image=?  "
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);        
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setString(4, product.getAddress());
            pstmt.setBytes(5, product.getImage());
            pstmt.setLong(6, product.getCategoryId());
            pstmt.executeUpdate();
            pstmt.close();
            this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.disconnect();
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try {
            //Validate product
            if (id <= 0) {
                return false;
            }
            this.connect();

            String sql = "DELETE FROM product "
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            pstmt.close();
            this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Product findById(Long id) {
        try {
            this.connect();
            String sql = "SELECT * FROM product  "
                    + "WHERE productId = ?";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                Product newProduct = new Product();
                newProduct.setProductId(res.getLong("ProductId"));
                newProduct.setName(res.getString("name"));
                newProduct.setDescription(res.getString("description"));
                newProduct.setPrice(res.getDouble("price"));
                newProduct.setAddress(res.getString("address"));
                newProduct.setCategoryId(res.getLong("categoryId"));
                newProduct.setImage(res.getBytes("image"));
                pstmt.close();
                this.disconnect();             
                return newProduct;
            } 
            

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.disconnect();
        return null;
    }

    @Override
    public List<Product> findByName(String pname) {
        List<Product> products = new ArrayList<>();
        try {

            String sql = "SELECT * FROM product"
                    + " WHERE name = ?";
            this.connect();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pname);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                Product newProduct = new Product();
                newProduct.setProductId(res.getLong("ProductId"));
                newProduct.setName(res.getString("name"));
                newProduct.setDescription(res.getString("description"));
                newProduct.setPrice(res.getDouble("price"));
                newProduct.setAddress(res.getString("address"));
                newProduct.setCategoryId(res.getLong("categoryId"));
                newProduct.setImage(res.getBytes("image"));
                pstmt.close();
                products.add(newProduct);
            }
            this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }

    public void cleanDatabase() {
        try {
            String sql = "DELETE FROM product";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public List<Product> findByCategory(Long categoryId) {
        List<Product> products = new ArrayList<>();
        try {                  
                this.connect();

                String productSql = "SELECT * FROM products WHERE categoryId = ?";
                PreparedStatement pstmt = conn.prepareStatement(productSql);
                pstmt.setLong(1, categoryId);
                ResultSet res = pstmt.executeQuery();

                while (res.next()) {
                    Product newProduct = new Product();
                    newProduct.setProductId(res.getLong("ProductId"));
                    newProduct.setName(res.getString("name"));
                    newProduct.setDescription(res.getString("description"));
                    newProduct.setPrice(res.getDouble("price"));
                    newProduct.setAddress(res.getString("address"));
                    newProduct.setCategoryId(res.getLong("categoryId"));
                    newProduct.setImage(res.getBytes("image"));
                    products.add(newProduct);
                }   
                pstmt.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.disconnect();
        return products;
    }

}
