/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.client.presentation.commands;

import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.client.domain.service.ProductService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author julian ruano
 */
public class OMDeleteProductCommand extends OMCommand{
    private int idProduct;
    private Product pP;
    private ProductService pS;
    boolean result=false;
    public OMDeleteProductCommand(int idProduct , ProductService pS){
        this.idProduct = idProduct;
        this.pS = pS;
    }

    @Override
    public void make() {
        try {
            pP = pS.findProductById(idProduct);
            result = pS.deleteProduct(idProduct);
        } catch (Exception ex) {
            Logger.getLogger(OMDeleteProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        }           
    }

    @Override
    public void unmake() {
        try {
            idProduct = pS.saveProduct(pP.getProductId(), pP.getName(), pP.getDescription(),pP.getPrice(),pP.getAddress(),pP.getCategoryId(),pP.getStock(),pP.getImage());
            result = idProduct > 0;
        } catch (Exception ex) {
            Logger.getLogger(OMDeleteProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public boolean result(){
        return result;
    } 
}
