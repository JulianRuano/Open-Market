package co.unicauca.openmarket.server.access;


import co.unicauca.openmarket.commons.domain.Category;
import java.util.List;



/**
 *
 * @author brayan majin, julian ruano
 */
public interface ICategoryRepository {
    
   int save(Category newCategory);
   boolean edit(int id, Category category);
   boolean delete(int id);
   Category findById(int id);
   List<Category> findAll();
   List<Category> findByName(String name);
   //boolean clearCategories();

}
