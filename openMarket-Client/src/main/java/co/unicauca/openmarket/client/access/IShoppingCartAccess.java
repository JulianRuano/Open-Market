package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.commons.application.Invoice;
import co.unicauca.openmarket.commons.application.PaymentDetails;


public interface IShoppingCartAccess {
    Invoice buy(Long id, PaymentDetails paymentMethod);
}
