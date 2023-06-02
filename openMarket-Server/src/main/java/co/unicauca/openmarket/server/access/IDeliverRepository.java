
package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.application.Invoice;
import java.util.List;

/**
 *
 * @author brayan
 */
public interface IDeliverRepository {
    
    double qualification(String idCompra, int puntuacion,int userID); 
    List<Invoice> billList(int userID);
    boolean priceToPay(String idCompra,int userID);
    
}
