package co.unicauca.openmarket.client.domain.service;



import java.util.ArrayList;
import java.util.List;
import co.unicauca.openmarket.client.access.IProductAccess;
import co.unicauca.openmarket.commons.domain.Product;
import reloj.frameworkobsobs.Observado;

/**
 *
 * @author Libardo, Julio
 */
public class ProductService extends Observado {
      
    // Ahora hay una dependencia de una abstracción, no es algo concreto,
    // no sabe cómo está implementado.
   public ProductService(){
    
   }
   
   
    private IProductAccess repository;

    /**
     * Inyección de dependencias en el constructor. Ya no conviene que el mismo
     * servicio cree un repositorio concreto
     *
     * @param repository una clase hija de IProductAccess
     */
    public ProductService(IProductAccess repository) {
        this.repository = repository;
    }
    

    public boolean saveProduct(Long productId, String name, String description, double price,String address ,Long categoryId, int stock,byte [] image)throws Exception {
        
        Product newProduct = new Product();
        newProduct.setProductId(productId);
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setPrice(price);
        newProduct.setAddress(address);
        newProduct.setCategoryId(categoryId);
        newProduct.setStock(stock);
        newProduct.setImage(image);
            
        //Validate product
        if (newProduct.getName().isBlank() ) {
            return false;
        }
        boolean result=repository.save(newProduct);
       // this.notificar();
        return result;

    }

    public List<Product> findAllProducts()throws Exception {
        List<Product> products = new ArrayList<>();
        products = repository.findAll();

        return products;
    }
    
    public Product findProductById(Long productId)throws Exception{
        return repository.findById(productId);
    }
    public List<Product> findProductsByName(String name)throws Exception {
        List<Product> products = new ArrayList<>();
        products = repository.findByName(name);

        return products;
    }
    public List<Product> findProductsByCategory(Long categoryId) throws Exception{
        List<Product> products = new ArrayList<>();
        products = repository.findByCategory(categoryId);

        return products;
    }
    public boolean deleteProduct(Long productId)throws Exception {
        boolean result=repository.delete(productId);
        this.notificar();
        return result;      
    }

    public boolean editProduct(Long productId, String name, String description, double price,String address ,Long categoryId, int stock,byte [] image) throws Exception{
          
        Product product = new Product();
        product.setProductId(productId);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setAddress(address);
        product.setCategoryId(categoryId);
        product.setStock(stock);
        product.setImage(image);
        
        //Validate product
        if (product.getProductId() == null ||product.getName().isBlank()) {
            return false;
        }
        boolean result= repository.edit(product);
        this.notificar();
        return result;

    }

}