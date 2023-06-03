
package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.application.Invoice;
import java.util.List;

/**
 *
 * @author brayan
 */
public interface IDeliverRepository {
    
    double qualification(String reference, int puntuacion,int userID); 
    List<Invoice> billList(int userID);
    boolean UpdateBalance(String reference,int userID);
    double balance(int userID);
}
