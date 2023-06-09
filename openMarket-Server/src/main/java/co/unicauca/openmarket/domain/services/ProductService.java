/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.domain.services;

import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.server.access.IProductRepository;
import java.util.List;

/**
 *
 * @author brayan
 */
public class ProductService {
   /**
     * Repositorio de Productos
     */
     IProductRepository repo;

     /**
     * Constructor parametrizado. Hace inyeccion de dependencias
     *
     * @param repo repositorio de tipo IProductRepository
     */ 
    public ProductService(IProductRepository repo) {
        this.repo = repo;
    }
     
    public synchronized int save(Product newProduct) {
        return repo.save(newProduct);
    }
    
    public synchronized boolean edit(Product product){
        return repo.edit(product);
    }
    
    public synchronized boolean delete(int id){
        return repo.delete(id);
    }
    public synchronized Product findById(int id){
        return repo.findById(id);
    };
    public synchronized List<Product> findAll(){
        return repo.findAll();
    };
    public synchronized List<Product> findByName(String name){
        return repo.findByName(name);
    };
    public synchronized List<Product> findByCategory(int categoryId){
        return repo.findByCategory(categoryId);
    };
    public synchronized List<Object> findNamePrice(int idProduct){
        return repo.findNamePrice(idProduct);
    }
     public synchronized List<Product> filterProducts(String prodName,Integer categoryId,Double minPrice,Double maxPrice){
        return repo.filterProducts(prodName,categoryId,minPrice,maxPrice);
    }
}
