
package co.unicauca.openmarket.client.domain.application;

import co.unicauca.openmarket.client.access.IShoppingCartAccess;
import co.unicauca.openmarket.commons.application.Invoice;
import co.unicauca.openmarket.commons.application.creditCard;


public class ShoppingCar {
    
    private IShoppingCartAccess repository;

    
    public ShoppingCar(IShoppingCartAccess repository){
        this.repository=repository;
    }
    public ShoppingCar(){
       
    }
    public Invoice buy(int id,creditCard paymentMethod, int idUser){
        return repository.buy(id,  paymentMethod, idUser);
    }
    
}
