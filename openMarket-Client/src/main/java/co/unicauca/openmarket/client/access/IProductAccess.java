
package co.unicauca.openmarket.client.access;


import co.unicauca.openmarket.commons.domain.Product;
import java.util.List;


/**
 *
 * @author Libardo, Julio
 */
public interface IProductAccess {
    int save(Product newProduct)throws Exception;
    
    boolean edit(Product producto)throws Exception;
    
    boolean delete(int id)throws Exception ;

    Product findById(int id)throws Exception;
    
   List<Product> findByName(String pname)throws Exception;
    List<Product> findByCategory(int categoryName)throws Exception;
    List<Product> findAll()throws Exception;
    List<Product> filterProducts(String prodName,int categoryId,double minPrice, double maxPrice)throws Exception;
    

}
