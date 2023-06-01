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
import co.unicauca.openmarket.client.access.IUserAccess;
import co.unicauca.openmarket.client.domain.application.ShoppingCar;
import co.unicauca.openmarket.client.domain.service.CategoryService;
import co.unicauca.openmarket.client.domain.service.ProductService;
import co.unicauca.openmarket.client.domain.service.UserService;
import co.unicauca.openmarket.presentacion.Dashboard;
import co.unicauca.openmarket.presentacion.GUILogin;
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
       IUserAccess repository4 = Factory.getInstance().getUserRepository("default");
        ProductService productService = new ProductService(repository);
        CategoryService categoryService=new CategoryService(repository2);
        ShoppingCar shoppingCart = new ShoppingCar(repository3);
        UserService userService=new UserService( repository4);
       // FlatMaterialLighterIJTheme.setup();   
        
       
       GUILogin login = new GUILogin(userService);
       Dashboard instance=new  Dashboard(productService,categoryService,shoppingCart,login);      
       login.setDasboard(instance);
       login.setService(productService, categoryService, shoppingCart);
       instance.setVisible(true);
       
    }
    
}