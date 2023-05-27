/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.domain.services;

import co.unicauca.openmarket.commons.domain.Category;
import co.unicauca.openmarket.server.access.ICategoryRepository;
import java.util.List;


/**
 *
 * @author brayan , Julian
 */
public class CategoryService {
     /**
     * Repositorio de Productos
     */
     ICategoryRepository repo;
     /**
     * Constructor parametrizado. Hace inyeccion de dependencias
     *
     * @param repo repositorio de tipo ICategoryRepository
     */
     public CategoryService(ICategoryRepository repo) {
        this.repo = repo;
    }
     /**
     * Buscar una categoria
     *
     * @param newCategory 
     * @return objeto tipo category
     */
    public synchronized int save(Category newCategory) { 
        return repo.save(newCategory);
    }
    
    public synchronized boolean edit(int id, Category category){
        return repo.edit(id, category);
    }
    
    public synchronized boolean delete(int id){
        return repo.delete(id);
    }
    public synchronized Category findById(int id){
        return repo.findById(id);
    };
    public synchronized List<Category> findAll(){
        return repo.findAll();
    };
    public synchronized List<Category> findByName(String name){
        return repo.findByName(name);
    };
}
