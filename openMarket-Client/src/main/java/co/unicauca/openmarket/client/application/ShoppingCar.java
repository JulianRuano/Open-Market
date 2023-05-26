
package co.unicauca.openmarket.client.application;

import co.unicauca.openmarket.commons.application.PaymentDetails;
import co.unicauca.openmarket.client.access.IShoppingCartAccess;
import co.unicauca.openmarket.commons.application.Invoice;


public class ShoppingCar {
    
    private IShoppingCartAccess repository;

    
    public ShoppingCar(IShoppingCartAccess repository){
        this.repository=repository;
    }
    
    public Invoice buy(Long id,PaymentDetails paymentMethod){
        return repository.buy(id,paymentMethod);
    }
    
}
