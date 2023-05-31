/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.client.main;

import co.unicauca.openmarket.client.access.Factory;
import co.unicauca.openmarket.client.access.ICategoryAccess;
import co.unicauca.openmarket.client.access.IProductAccess;
import co.unicauca.openmarket.client.access.IShoppingCartAccess;
import co.unicauca.openmarket.client.domain.application.ShoppingCar;
import co.unicauca.openmarket.client.domain.service.CategoryService;
import co.unicauca.openmarket.client.domain.service.ProductService;
import co.unicauca.openmarket.presentacion.Dashboard;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;

/**
 *
 * @author brayan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       IProductAccess repository = Factory.getInstance().getRepository("default");
       ICategoryAccess repository2 =  Factory.getInstance().getCatRepository("default");
       IShoppingCartAccess repository3 = Factory.getInstance().getShoppingRepository("default");
        ProductService productService = new ProductService(repository);
        CategoryService categoryService=new CategoryService(repository2);
        ShoppingCar shoppingCart = new ShoppingCar(repository3);
       // FlatMaterialLighterIJTheme.setup();   
        
/*
        GUICategory instance1=new GUICategory(categoryService);
        instance1.setVisible(true);
        instance1.setSize(595, 380);
        instance1.setLocation(0,0);
        GUIProducts instance2 = new GUIProducts(productService,shoppingCart);
        
        instance2.setVisible(true);
        instance2.setLocation(590, 0);
         
        GUIProductsFind instance3 = new GUIProductsFind(null,false,productService);
        instance3.setVisible(true);
        productService.addObservador(instance3);
        
        GUICategoriesFind instance4 = new GUICategoriesFind (null,false,categoryService);
        instance4.setVisible(true);
        categoryService.addObservador(instance4);
       */
       Dashboard instance1=new  Dashboard(productService,categoryService,shoppingCart);
       instance1.setVisible(true);
       
    }
    
}