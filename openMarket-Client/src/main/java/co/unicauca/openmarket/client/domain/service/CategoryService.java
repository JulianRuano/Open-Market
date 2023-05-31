
package co.unicauca.openmarket.client.domain.service;

import co.unicauca.openmarket.client.access.ICategoryAccess;
import co.unicauca.openmarket.commons.domain.Category;


import java.util.List;
import reloj.frameworkobsobs.Observado;

/**
 *
 * @author brayan majin, julian ruano
 */
public class CategoryService extends Observado{
    
    private int idCategory;
    
    
    
    public CategoryService(){
        
    }
    private ICategoryAccess repository;
    
    public CategoryService(ICategoryAccess repository){
        this.repository=repository;
    }
    public boolean saveCategory (int id,String name)throws Exception{
        Category newCategory=new Category();
        newCategory.setCategoryId(id);
        newCategory.setName(name);
        if(newCategory.getName().isBlank()){
            return false;
        }
        boolean result= false;
        
        this.idCategory = repository.save(newCategory);
        
        if (idCategory > 0 ){        
            result= true;
        }       
        
        this.notificar();
        return result;
    }
    
    public boolean editCategory(int categoryId,Category cat) {
        
        //Validate product
        if(cat==null || cat.getName().isBlank()){
            return false;
        }
      
       boolean result=repository.edit(categoryId,cat);
       this.notificar();
       return result;
    }
    
   public boolean deleteCategory(int id){
       boolean result =repository.delete(id);
       this.notificar();
        return result;
    }  
    public Category findCategoryById(int id)throws Exception{
        return repository.findById(id);
    }
       public List<Category> findAllCategories(){
        return repository.findAll();
    }
       
       public List<Category> findCategoriesByName(String name)throws Exception{
        return repository.findByName(name);
    }

    public int getIdCategory() {
        return idCategory;
    }
    
       
}  
        
