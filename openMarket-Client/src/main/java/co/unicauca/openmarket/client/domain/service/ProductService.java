package co.unicauca.openmarket.client.domain.service;

import java.util.ArrayList;
import java.util.List;
import co.unicauca.openmarket.client.access.IProductAccess;
import co.unicauca.openmarket.commons.domain.Product;
import reloj.frameworkobsobs.Observado;

/**
 *
 * @author brayan,jorge,freider,julian
 */
public class ProductService extends Observado {

    private int idProduct;

    // Ahora hay una dependencia de una abstracción, no es algo concreto,
    // no sabe cómo está implementado.
    public ProductService() {

    }

    private IProductAccess repository;

    /**
     * Inyección de dependencias en el constructor. Ya no conviene que el mismo
     * servicio cree un repositorio concreto
     *
     * @param repository una clase hija de IProductAccess
     */
    public int getIdProduct() {
        return idProduct;
    }

    public ProductService(IProductAccess repository) {
        this.repository = repository;
    }

    public int saveProduct(int productId, String name, String description, double price, String address, int categoryId, int stock, byte[] image) throws Exception {

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
        if (newProduct.getName().isBlank()) {
            return 0;
        }
        this.idProduct = repository.save(newProduct);
        this.notificar();
        return idProduct;
    }

    public List<Product> findAllProducts() throws Exception {
        return repository.findAll();
    }

    public Product findProductById(int productId) throws Exception {
        return repository.findById(productId);
    }

    public List<Product> findProductsByName(String name) throws Exception {
        return repository.findByName(name);
    }

    public List<Product> findProductsByCategory(int categoryId) throws Exception {
        return repository.findByCategory(categoryId);
    }

    public List<Product> filterProducts(String prodName, Integer categoryId, Double minPrice, Double maxPrice) throws Exception {
        return repository.filterProducts(prodName, categoryId, minPrice, maxPrice);

    }

    public boolean deleteProduct(int productId) throws Exception {
        boolean result = repository.delete(productId);
        this.notificar();
        return result;
    }

    public boolean editProduct(int productId, String name, String description, double price, String address, int categoryId, int stock, byte[] image) throws Exception {

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
        if (product.getName().isBlank()) {
            return false;
        }
        boolean result = repository.edit(product);
        this.notificar();
        return result;

    }

}
