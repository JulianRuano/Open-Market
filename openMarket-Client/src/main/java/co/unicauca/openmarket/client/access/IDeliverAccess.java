
package co.unicauca.openmarket.client.access;

import co.unicauca.openmarket.commons.application.Invoice;
import java.util.List;

/**
 *
 * @author brayan
 */
public interface IDeliverAccess {
    double qualification(String reference, int punctuation,int userID)throws Exception; 
    List<Invoice> billList(int userID)throws Exception ;
    double priceToPay(String reference,int userID)throws Exception;
}
