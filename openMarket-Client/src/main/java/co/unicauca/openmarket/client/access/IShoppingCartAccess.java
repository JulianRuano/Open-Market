package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.commons.application.Invoice;
import co.unicauca.openmarket.commons.application.creditCard;


public interface IShoppingCartAccess {
    Invoice buy(int id, creditCard paymentMethod, int idUser);
}
