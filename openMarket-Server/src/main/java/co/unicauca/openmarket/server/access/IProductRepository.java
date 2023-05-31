
package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.Product;

import java.util.List;


/**
 *
 * @author Libardo, Julio
 */
public interface IProductRepository {
    int save(Product newProduct);
    
    boolean edit(Product newProduct);
    
    boolean delete(int id);

    Product findById(int id);
    
   List<Product> findByName(String pname);
   List<Product> findByCategory(int categoryName);
   List<Product> findAll();
   List<Object> findNamePrice(int idProduct);
   List<Product> filterProducts(String prodName,Integer categoryId,Double minPrice,Double maxPrice);
        

}
