package co.unicauca.openmarket.client.presentation.commands;


import co.unicauca.openmarket.client.domain.service.ProductService;
import co.unicauca.openmarket.commons.domain.Product;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author julian ruano
 */
public class OMAddProductCommand extends OMCommand{
    private Product pP;
    private int idProduct;
    private ProductService pS;
    boolean result=false;
    public OMAddProductCommand(Product pP, ProductService pS){
        this.pP = pP;
        this.pS = pS;
    }
    
    
    @Override
    public void make() {
        try {
            this.idProduct = pS.saveProduct(pP.getProductId(), pP.getName(), pP.getDescription(),pP.getPrice(),pP.getAddress(),pP.getCategoryId(),pP.getStock(),pP.getImage());
            result = idProduct > 0;
        } catch (Exception ex) {
            Logger.getLogger(OMAddProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void unmake() {
        try {
            result = pS.deleteProduct(idProduct);
        } catch (Exception ex) {
            Logger.getLogger(OMAddProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public boolean result(){
        return result;
    }
}
